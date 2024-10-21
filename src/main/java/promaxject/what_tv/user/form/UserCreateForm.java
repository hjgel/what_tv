package promaxject.what_tv.user.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "닉네임을 입력하세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인을 입력하세요.")
    private String password2;

    @NotEmpty(message = "이메일을 입력하세요.")
    @Email
    private String email;
}
