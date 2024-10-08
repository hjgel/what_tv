package promaxject.what_tv.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import promaxject.what_tv.user.domain.user.User;
import promaxject.what_tv.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User create(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);

        return user;
    }
//    @Transactional
//    public Long join(UserDto dto) {
//        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
//
//        return userRepository.save(dto.toEntity()).getId();
//        // 사용자 비밀번호를 해쉬 암호화 후 레포지토리에 저장한다.
//    }
}
