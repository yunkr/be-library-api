package library.librarymanagement.book.dto;

import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class BookPostDto {

    @Positive
    private Long memberId;

    @Positive
    private Long categoryId;

    @NotBlank(message = "책 제목을 작성해주세요.")
    private String bookName;

    @NotBlank(message = "책 저자를 작성해주세요.")
    private String author;

    @NotBlank(message = "책 출판사를 작성해주세요.")
    private String publisher;

    @NotBlank(message = "책 내용을 작성해주세요.")
    private String bookContent;

    @NotBlank(message = "책 목차를 작성해주세요.")
    private String bookIndex;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
