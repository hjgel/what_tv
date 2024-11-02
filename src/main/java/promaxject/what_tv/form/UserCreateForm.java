package promaxject.what_tv.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserCreateForm {


    @Size(min = 3, max = 25)
    @NotEmpty(message = "username 입력하세요.")
    private String username;

    @Size(min = 2, max = 80, message = "별명은 최소 2글자, 최대 80글자로 지정해주세요.")
    @NotEmpty(message = "사이트 안에서 사용할 별명을 입력하세요.")
    private String nickname;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인을 입력하세요.")
    private String password2;

    @NotEmpty(message = "지역을 정해주세요.")
    private String region;

    @NotEmpty(message = "이메일을 입력하세요.")
    @Email
    private String email;
}
