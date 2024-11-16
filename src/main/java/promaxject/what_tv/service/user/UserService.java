package promaxject.what_tv.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import promaxject.what_tv.exception.DataNotFoundException;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser getUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException(username));
    }

    // 사용자 정보 수정 로직
    @Transactional
    public void updateUserInfo(String username, SiteUser updatedUser) {
        SiteUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾지 못하였습니다."));

        // 수정 가능한 정보 설정

        user.setNickname(updatedUser.getNickname());
        user.setPNumber(updatedUser.getPNumber());
        user.setRegion(updatedUser.getRegion());
        user.setEmail(updatedUser.getEmail());

        userRepository.save(user);
    }


    public SiteUser create(String username, String nickname, String password, String email, String region, String pNumber) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("중복된 닉네임이 있습니다.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("중복된 이메일이 있습니다.");
        }
        if(userRepository.existsBypNumber(pNumber)) {
            throw new IllegalArgumentException("중복된 휴대전화번호 사용자가 있습니다.");
        }

        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRegion(region);
        user.setPNumber(pNumber);
        user.setRoles(Set.of("ROLE_USER"));
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
