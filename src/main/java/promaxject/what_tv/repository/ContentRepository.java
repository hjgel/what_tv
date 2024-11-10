package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Post;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @EntityGraph(attributePaths = {"imageList"})
    Optional<Content> findWithImagesById(Long id);
}
