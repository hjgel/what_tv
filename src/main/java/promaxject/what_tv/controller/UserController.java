package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import promaxject.what_tv.domain.Post;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.dto.ProfileImageDto;
import promaxject.what_tv.dto.ProfileImageResponseDto;
import promaxject.what_tv.form.UserCreateForm;
//import promaxject.what_tv.service.user.ProfileImageService;
import promaxject.what_tv.service.post.PostService;
import promaxject.what_tv.service.user.ProfileImageService;
import promaxject.what_tv.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ProfileImageService profileImageService;
    private final PostService postService;

    @GetMapping("/mypage")
    public String myPage(Principal principal, Model model, Authentication authentication) {

        String username = principal.getName();
        SiteUser user = userService.getUsername(username);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        ProfileImageResponseDto image = profileImageService.findImage(userDetails.getUsername());


        model.addAttribute("image", image);
        model.addAttribute("user", user);
        return "my_page";
    }

    @GetMapping("/mypage/posts")
    public String myPosts(Principal principal, Model model) {
        String username = principal.getName();
        SiteUser user = userService.getUsername(username);

        // 사용자가 작성한 게시글 목록 조회
        List<Post> myPosts = postService.getPostByUser(user);
        model.addAttribute("myPosts", myPosts);

        return "my_posts";
    }

//    @GetMapping("/info")
//    public String memberInfo(Model model, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        ProfileImageResponseDto image = profileImageService.findImage(userDetails.getUsername());
//
//        model.addAttribute("image", image);
//
//        return "/my_page";
//    }


    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm, Model model) {
        List<String> regions = Arrays.asList("서울", "부산", "인천", "대전", "대구", "광주", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주");
        model.addAttribute("regions", regions);
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if(bindingResult.hasErrors()) {
            List<String> regions = Arrays.asList("서울", "부산", "인천", "대전", "대구", "광주", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주");
            model.addAttribute("regions", regions);
            return "signup_form";
        }


        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(),userCreateForm.getNickname(), userCreateForm.getPassword1(), userCreateForm.getEmail(), userCreateForm.getRegion());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }


        redirectAttributes.addFlashAttribute("signupSuccess", true);
        return "signupsucess";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
