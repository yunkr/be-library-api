package library.librarymanagement.member.service;

import library.librarymanagement.exception.BusinessLogicException;
import library.librarymanagement.exception.ExceptionCode;
import library.librarymanagement.loan.repository.LoanRepository;
import library.librarymanagement.member.dto.MemberResponseDto;
import library.librarymanagement.member.entity.Member;
import library.librarymanagement.member.mapper.MemberMapper;
import library.librarymanagement.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final LoanRepository loanRepository;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper, LoanRepository loanRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.loanRepository = loanRepository;
    }

    public Member createMember(Member member) {

        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {

        Member findMember = findVerifyMember(member.getMemberId());

        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));

        Optional.ofNullable(member.getDisplayName())
                .ifPresent(displayName -> findMember.setDisplayName(displayName));

        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));


        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {

        Member findMember = memberRepository.findById(memberId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public Page<Member> findMembers(int page, int size) {

        return memberRepository.findAll(PageRequest.of(page, size));
    }

    public Member deleteMember(long memberId) {

        Member findMember = findMember(memberId);
        memberRepository.delete(findMember);

        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    // Member를 수정하기 위해선 Member가 있는지 검증
    public Member findVerifyMember(long memberId) {

        // Optional : Null값 허용
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    // 대출 중인 책이 5권 이상 이거나 연체중인 책이 있는 경우에는 책을 대출할 수 없다.
    public boolean canBorrowBooks(Member member) {
        long borrowedBooksCount = loanRepository.countByMemberAndReturnDateIsNull(member);
        boolean hasOverdueBooks = loanRepository.existsByMemberAndDueDateBeforeAndReturnDateIsNull(member, LocalDateTime.now());

        return borrowedBooksCount < 5 && !hasOverdueBooks;
    }
}
