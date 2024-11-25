package promaxject.what_tv.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "email"})
})

public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String region;

    @Column
    private String nickname;

    @Column(name = "p_number")
    private String pNumber;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ProfileImage image;

    // 역할 컬렉션 추가
    @ElementCollection(fetch = FetchType.EAGER)  // roles를 항상 즉시 로드
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;  // 유저의 역할을 저장하는 컬렉션

    // ROLE_ADMIN 여부 체크하는 메서드
    public boolean isAdmin() {
        return roles.contains("ADMIN");
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Balance balance;

}
