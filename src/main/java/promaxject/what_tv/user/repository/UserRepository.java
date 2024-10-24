package promaxject.what_tv.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.user.SiteUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    // username으로 중복된 사용자가 있는지 확인
    boolean existsByUsername(String username);

    // email로 중복된 사용자가 있는지 확인
    boolean existsByEmail(String email);

    Optional<SiteUser> findByUsername(String username);

    // select * from User where username = ?;
//    Optional<User> findByUsername(String username);
}
