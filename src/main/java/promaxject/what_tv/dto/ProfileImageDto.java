package promaxject.what_tv.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileImageDto {
    private MultipartFile file;
}
