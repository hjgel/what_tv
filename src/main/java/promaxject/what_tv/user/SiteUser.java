package promaxject.what_tv.user;

import lombok.Data;
import promaxject.what_tv.profile.ProfileImage;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ProfileImage image;

}
