package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
