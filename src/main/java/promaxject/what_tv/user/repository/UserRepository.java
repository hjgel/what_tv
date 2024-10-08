package promaxject.what_tv.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.user.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // select * from User where username = ?;
//    Optional<User> findByUsername(String username);
}
