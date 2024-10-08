package promaxject.what_tv.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.repository.QuestionRepository;
import promaxject.what_tv.qna.service.QuestionService;

import java.util.List;

@RequiredArgsConstructor // 이거 쓰면 생성자 굳이 안 만들어도 됨.
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // 리스트에 있는 본인 id를 http주소로 쏴주어서 매핑함.
    @GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question); // id라는 이름으로 value 객체 추가
        return "question_detail";
    }
}
