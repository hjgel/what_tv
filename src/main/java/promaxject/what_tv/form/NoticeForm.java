package promaxject.what_tv.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class NoticeForm {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(max=100)
    private String title;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

}
