package promaxject.what_tv.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="create_at")
    private LocalDateTime createAt;

    @Column(name="modify_at")
    private LocalDateTime modifyAt;

    // 관계 주입
    @ManyToOne
    private Question question;


    @ManyToOne
    private SiteUser author;
}
