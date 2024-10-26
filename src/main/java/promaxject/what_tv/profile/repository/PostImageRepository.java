package promaxject.what_tv.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.profile.ProfileImage;
import promaxject.what_tv.user.SiteUser;

public interface PostImageRepository extends JpaRepository<ProfileImage, Long> {
    ProfileImage findByUser(SiteUser user);
}
