package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import promaxject.what_tv.dto.PostImageDto;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.form.ContentForm;
import promaxject.what_tv.service.post.PostService;
import promaxject.what_tv.form.PostForm;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    // 리스트에 있는 본인 id를 http주소로 쏴주어서 매핑함.
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") long id, ContentForm contentForm) {
        Post post = this.postService.getPost(id);
        model.addAttribute("post", post); // id라는 이름으로 value 객체 추가
        return "post_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String postCreate(PostForm postForm) {
        return "post_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid PostForm postForm, BindingResult bindingResult, Principal principal, @ModelAttribute PostImageDto postImageDto, @RequestParam("price") Integer price) {
        // 질문 저장
        if (bindingResult.hasErrors()) {
            return "post_form";
        }

        logger.info("PostImageDto is {}", postImageDto);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.postService.create(postForm.getTitle(), postForm.getContent(), siteUser, postImageDto, price);
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Post> paging = this.postService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "post_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String postsModify(PostForm postForm, @PathVariable("id") Long id, Principal principal) {
        Post post = this.postService.getPost(id);
        if(!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());
        return "post_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid PostForm postForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "post_form";
        }

        Post post = this.postService.getPost(id);
        if(!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.postService.modify(post, postForm.getTitle(), postForm.getContent());
        return String.format("redirect:/post/detail/%s", id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Long id) {
        Post post = this.postService.getPost(id);
        if(!post.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.postService.delete(post);
        return "redirect:/post/list";
    }
}
