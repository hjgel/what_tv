package promaxject.what_tv.domain;

import lombok.Data;

import javax.persistence.*;

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

}
