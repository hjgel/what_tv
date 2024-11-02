package promaxject.what_tv.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import promaxject.what_tv.domain.Image;
import promaxject.what_tv.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String author;
    private List<String> imageUrls;

    @Builder
    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
        this.author = post.getAuthor().getUsername();
        this.imageUrls = post.getImageList().stream().map(Image::getUrl).collect(Collectors.toList());
    }
}
