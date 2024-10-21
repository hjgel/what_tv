package promaxject.what_tv.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import promaxject.what_tv.exception.DataNotFoundException;
import promaxject.what_tv.user.SiteUser;
import promaxject.what_tv.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public SiteUser create(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 닉네임이 있습니다.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 있습니다.");
        }

        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);

        return user;
    }
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = userRepository.findByUsername(username);
        if(siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found.");
        }
    }
}
