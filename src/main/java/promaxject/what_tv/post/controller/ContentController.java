package promaxject.what_tv.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import promaxject.what_tv.post.Post;
import promaxject.what_tv.post.form.ContentForm;
import promaxject.what_tv.post.service.ContentService;
import promaxject.what_tv.post.service.PostService;
import promaxject.what_tv.qna.Question;
import promaxject.what_tv.qna.form.AnswerForm;

import promaxject.what_tv.user.SiteUser;
import promaxject.what_tv.user.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/content")
@RequiredArgsConstructor
@Controller
public class ContentController {
    private final PostService postService;
    private final ContentService contentService;
    private final UserService userService;
    // 비즈니스 로직을 처리하는 서비스. 질문과 관련된 데이터를 처리하는 역할.

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid ContentForm contentForm, BindingResult bindingResult
            , Principal principal) {
        Post post = this.postService.getPost(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "post_detail";
        }

        this.contentService.create(post, contentForm.getContent(), siteUser);

        // 답변을 저장한다.
//        this.answerService.create(question, content);

        return String.format("redirect:/post/detail/%s", id);
    }
}
