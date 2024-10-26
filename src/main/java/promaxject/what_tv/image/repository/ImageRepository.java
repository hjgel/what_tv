package promaxject.what_tv.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.image.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
