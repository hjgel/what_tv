package promaxject.what_tv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequestDto {
    @NotEmpty(message = "휴대폰 번호를 입력해주세요")
    private String p_number;
}
