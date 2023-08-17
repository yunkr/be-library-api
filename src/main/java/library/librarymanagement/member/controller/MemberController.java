package library.librarymanagement.member.controller;

import library.librarymanagement.dto.MultiResponseDto;
import library.librarymanagement.dto.SingleResponseDto;
import library.librarymanagement.member.dto.MemberPatchDto;
import library.librarymanagement.member.dto.MemberPostDto;
import library.librarymanagement.member.entity.Member;
import library.librarymanagement.member.mapper.MemberMapper;
import library.librarymanagement.member.repository.MemberRepository;
import library.librarymanagement.member.service.MemberService;
import library.librarymanagement.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

/* Todo
1. 사용자 API
- 등록/조회(단건)/수정/삭제

2. 도서 API
- 도서 목록 조회 - queryDSL
- 도서 단건 조회

3. 도서관 목록 API
 */

@RestController
@RequestMapping("/members")
@Validated
public class MemberController {

    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberRepository memberRepository, MemberService memberService, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping
    public ResponseEntity<?> postMember(@Valid @RequestBody MemberPostDto requestBody) {

        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(requestBody));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId,
                                         @Valid @RequestBody MemberPatchDto requestBody) {

        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)), HttpStatus.OK);

    }

    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") @Positive long memberId) {

        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMembers(@RequestParam int page, @RequestParam int size) {

        Page<Member> pageMembers = memberService.findMembers(page - 1, size);

        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.memberToMemberResponseDtos(members), pageMembers)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
