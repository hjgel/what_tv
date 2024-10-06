package promaxject.what_tv.board.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import promaxject.what_tv.board.domain.repository.BoardRepository;
import promaxject.what_tv.board.dto.BoardDto;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
