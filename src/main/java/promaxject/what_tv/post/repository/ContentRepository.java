package promaxject.what_tv.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.post.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
