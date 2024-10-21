package promaxject.what_tv.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnswerForm {
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
}
