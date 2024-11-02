package promaxject.what_tv.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import promaxject.what_tv.exception.DataNotFoundException;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser getUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException(username));
    }


    public SiteUser create(String username, String nickname, String password, String email, String region) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 닉네임이 있습니다.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 있습니다.");
        }

        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRegion(region);
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