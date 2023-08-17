package library.librarymanagement.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class BookPatchDto {

    @Positive
    private Long bookId;

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

}
