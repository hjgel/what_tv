package promaxject.what_tv.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 공지사항 ID (자동 생성)

    private String title; // 공지사항 제목

    @Lob
    private String content; // 공지사항 내용

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성 시간

    // 기본 생성자
    public Notice() {
        this.createdAt = LocalDateTime.now(); // 공지사항이 생성될 때 현재 시간을 설정
    }

    @ManyToOne
    private SiteUser author;

    // 생성자를 통해 title과 content만 받도록 설정
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now(); // 공지사항이 생성될 때 현재 시간을 설정
    }
}