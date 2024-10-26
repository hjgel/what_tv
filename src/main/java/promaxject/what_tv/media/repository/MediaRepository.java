package promaxject.what_tv.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.media.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
