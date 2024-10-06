package promaxject.what_tv.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import promaxject.what_tv.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
