package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import promaxject.what_tv.domain.Content;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.dto.ContentImageDto;
import promaxject.what_tv.dto.PostImageDto;
import promaxject.what_tv.form.ContentForm;
import promaxject.what_tv.repository.ContentRepository;
import promaxject.what_tv.service.post.ContentService;
import promaxject.what_tv.service.post.PostService;

import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/content")
@RequiredArgsConstructor
@Controller
public class ContentController {
    private final PostService postService;
    private final ContentService contentService;
    private final UserService userService;
    private final ContentRepository contentRepository;
    // 비즈니스 로직을 처리하는 서비스. 질문과 관련된 데이터를 처리하는 역할.

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable("id") Long id,
                               @Valid ContentForm contentForm,
                               BindingResult bindingResult,
                               Principal principal,
                               @ModelAttribute ContentImageDto contentImageDto) { // @ModelAttribute로 수정

        Post post = this.postService.getPost(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "post_detail";
        }

        // 컨텐츠 생성 서비스 호출
        this.contentService.create(post, contentForm.getContent(), siteUser, contentImageDto);

        return String.format("redirect:/post/detail/%s", id);
    }
}
