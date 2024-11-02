package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
