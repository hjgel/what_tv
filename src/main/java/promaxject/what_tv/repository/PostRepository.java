package promaxject.what_tv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.domain.SiteUser;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAll(Specification<Post> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"imageList"})
    Optional<Post> findWithImagesById(Long id);

    List<Post> findByAuthor(SiteUser author);
}
