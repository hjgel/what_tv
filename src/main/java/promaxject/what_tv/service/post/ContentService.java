package promaxject.what_tv.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Image;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.dto.ContentImageDto;
import promaxject.what_tv.exception.DataNotFoundException;
import promaxject.what_tv.repository.ContentRepository;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final ImageRepository imageRepository;


    @Value("${file.boardImagePath}")
    private String uploadFolder;

//    public Content getContent(Long id) {
//        Content cont = contentRepository.findWithImagesById(id)
//                .orElseThrow(() -> new DataNotFoundException("Post not found"));
//
//        return cont;
//    }

    public void create(Post post, String content, SiteUser author, ContentImageDto contentImageDto) {
        Content con = new Content();
        con.setContent(content);
        con.setCreateAt(LocalDateTime.now());
        con.setAuthor(author);
        con.setPost(post);
        this.contentRepository.save(con);

        if(contentImageDto.getFiles() != null && !contentImageDto.getFiles().isEmpty()) {
            for (MultipartFile file : contentImageDto.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();

                File destinationFile = new File(uploadFolder + imageFileName);
                destinationFile.getParentFile().mkdirs();
                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Image image = Image.builder().url("/postImages/" + imageFileName).content(con).build();

                imageRepository.save(image);
            }
        }
    }
}
