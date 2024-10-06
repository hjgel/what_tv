//package promaxject.what_tv.user.dto;
//
//import com.group.what_tv.user.domain.user.Role;
//import com.group.what_tv.user.domain.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class UserDto {
//
//    private String username;
//    private String password;
//    private String email;
//    private Role role;
//
//    // DTO -> Entity
//    public User toEntity() {
//        User user = User.builder().
//                username(username).
//                password(password).
//                email(email).
//                role(role.USER).
//                build();
//        return user;
//    }
//}
