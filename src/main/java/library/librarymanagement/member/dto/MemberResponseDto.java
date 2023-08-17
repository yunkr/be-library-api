package library.librarymanagement.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {

    private Long memberId;

    private String email;

    private String password;

    private String displayName;

    private String phone;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
