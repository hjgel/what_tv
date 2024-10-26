package promaxject.what_tv.post.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContentForm {
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
}
