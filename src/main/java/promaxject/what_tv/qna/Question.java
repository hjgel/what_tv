package promaxject.what_tv.qna;


import lombok.Data;
import lombok.NoArgsConstructor;
import promaxject.what_tv.user.SiteUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "question")
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name="create_at")
    private LocalDateTime createAt;

    // 관계 주입
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @Column(name="modify_at")
    private LocalDateTime modifyAt;


    @ManyToOne
    private SiteUser author;
}
