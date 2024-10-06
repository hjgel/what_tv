package promaxject.what_tv.board.dto;

import lombok.*;
import promaxject.what_tv.board.domain.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime create_at;
    private LocalDateTime update_at;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder().title(title).id(id).content(content).build();
        return boardEntity;
    }

    @Builder
    public BoardDto(Long id, String title, String content, LocalDateTime create_at, LocalDateTime update_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.create_at = create_at;
        this.update_at = update_at;
    }
}
