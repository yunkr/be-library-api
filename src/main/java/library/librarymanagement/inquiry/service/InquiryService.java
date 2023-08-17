package library.librarymanagement.inquiry.service;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.exception.BusinessLogicException;
import library.librarymanagement.exception.ExceptionCode;
import library.librarymanagement.inquiry.entity.Inquiry;
import library.librarymanagement.inquiry.mapper.InquiryMapper;
import library.librarymanagement.inquiry.repository.InquiryRepository;
import library.librarymanagement.member.entity.Member;
import library.librarymanagement.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberService memberService;

    public InquiryService(InquiryRepository inquiryRepository, MemberService memberService) {
        this.inquiryRepository = inquiryRepository;
        this.memberService = memberService;
    }

    public Inquiry createInquiry(Inquiry inquiry) {

        verifyInquiry(inquiry);

        return inquiryRepository.save(inquiry);
    }

    public Inquiry updateInquiry(Inquiry inquiry) {

        Inquiry findInquiry = findInquiry(inquiry.getInquiryId());

        Optional.ofNullable(inquiry.getTitle())
                .ifPresent(title -> findInquiry.setTitle(title));

        Optional.ofNullable(inquiry.getContent())
                .ifPresent(content -> findInquiry.setContent(content));

        return inquiryRepository.save(findInquiry);
    }

    public Inquiry findInquiry(long inquiryId) {

        Inquiry findInquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.INQUIRY_NOT_FOUND));

        return findInquiry;
    }

    public Page<Inquiry> findInquiries(int page, int size) {

        return inquiryRepository.findAll(PageRequest.of(page, size));
    }

    public Inquiry deleteInquiry(long inquiryId) {

        Inquiry findInquiry = findInquiry(inquiryId);
        inquiryRepository.delete(findInquiry);

        return findInquiry;

    }

    // member 가 존재하는지 확인
    public void verifyInquiry(Inquiry inquiry) {

        Member member = memberService.findMember(inquiry.getMember().getMemberId());
        inquiry.setMember(member);

    }
}
