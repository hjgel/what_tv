package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.domain.ProfileImage;
import promaxject.what_tv.domain.SiteUser;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    ProfileImage findByUser(SiteUser user);
}
