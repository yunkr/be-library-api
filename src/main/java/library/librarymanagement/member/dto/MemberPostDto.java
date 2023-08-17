package library.librarymanagement.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MemberPostDto {

    @NotBlank(message = "이메일을 작성해주세요.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String password;

    @NotBlank(message = "닉네임을 작성해주세요.")
    private String displayName;

    @NotBlank(message = "전화번호를 작성해주세요.")
    private String phone;

}
