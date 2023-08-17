package library.librarymanagement.inquiry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class InquiryPatchDto {

    @Positive
    private Long inquiryId;

    @NotBlank(message = "문의 글 제목을 넣어주세요.")
    private String title;

    @NotBlank(message = "문의 글 내용을 넣어주세요.")
    private String content;

}
