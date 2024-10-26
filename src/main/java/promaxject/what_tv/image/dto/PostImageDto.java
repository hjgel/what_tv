package promaxject.what_tv.image.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostImageDto {
    private List<MultipartFile> files;
}
