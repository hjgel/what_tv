package promaxject.what_tv.qna.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import promaxject.what_tv.form.AnswerForm;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.service.AnswerService;
import promaxject.what_tv.qna.service.QuestionService;
import promaxject.what_tv.user.SiteUser;
import promaxject.what_tv.user.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answer")  // URL 패턴 지정. answer URL에 대한 요청을 처리하는 컨트롤러
@RequiredArgsConstructor  // Lombok제공. final이 붙은 필드에 대해 자동으로 생성자 생성
@Controller // Spring MVC에서 웹 컨트롤러로 정의하는 어노테이션  HTTP 요청을 처리하고 응답 반환 역할 수행
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    // 비즈니스 로직을 처리하는 서비스. 질문과 관련된 데이터를 처리하는 역할.

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult
                                , Principal principal) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        this.answerService.create(question, answerForm.getContent(), siteUser);

        // 답변을 저장한다.
//        this.answerService.create(question, content);

        return String.format("redirect:/question/detail/%s", id);
    }
}
