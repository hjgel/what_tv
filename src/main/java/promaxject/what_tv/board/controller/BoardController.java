//package promaxject.what_tv.board.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import promaxject.what_tv.board.dto.BoardDto;
//import promaxject.what_tv.board.service.BoardService;
//
//@Controller
//@AllArgsConstructor
//public class BoardController {
//    private final BoardService boardService;
//
//    @GetMapping("/")
//    public String list() {
//        return "board/list.html";
//    }
//
//    @GetMapping("/post")
//    public String write() {
//        return "board/write.html";
//    }
//
//    @PostMapping("/post")
//    public String write(BoardDto boardDto) {
//        boardService.savePost(boardDto);
//
//        return "redirect:/";
//    }
//
//}
