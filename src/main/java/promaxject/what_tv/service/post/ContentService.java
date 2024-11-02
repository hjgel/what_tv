package promaxject.what_tv.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.repository.ContentRepository;
import promaxject.what_tv.domain.SiteUser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ContentService {
    private final ContentRepository contentRepository;

    public void create(Post post, String content, SiteUser author) {
        Content con = new Content();
        con.setContent(content);
        con.setCreateAt(LocalDateTime.now());
        con.setAuthor(author);
        con.setPost(post);
        this.contentRepository.save(con);
    }
}
