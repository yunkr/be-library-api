package library.librarymanagement.inquiry.dto;

import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class InquiryPostDto {

    @Positive
    private Long memberId;

    @NotBlank(message = "문의 글 제목을 넣어주세요.")
    private String title;

    @NotBlank(message = "문의 글 내용을 넣어주세요.")
    private String content;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

}
