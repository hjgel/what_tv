package promaxject.what_tv.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import promaxject.what_tv.post.Content;
import promaxject.what_tv.post.Post;
import promaxject.what_tv.post.repository.ContentRepository;
import promaxject.what_tv.qna.Answer;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.user.SiteUser;

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
