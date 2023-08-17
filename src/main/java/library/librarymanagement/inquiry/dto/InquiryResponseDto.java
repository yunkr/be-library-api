package library.librarymanagement.inquiry.dto;

import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class InquiryResponseDto {

    private Long inquiryId;

    private Long memberId;

    private String title;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public void setMember(Member member) {
        if (member != null) {
            this.memberId = member.getMemberId();
        }
    }

}
