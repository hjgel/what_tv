package promaxject.what_tv.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileImageResponseDto {
    private String url;

    @Builder
    public ProfileImageResponseDto(String url) {
        this.url = url;
    }
}
