package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import promaxject.what_tv.dto.ProfileImageDto;
import promaxject.what_tv.service.user.ProfileImageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ProfileImageController {

    private final ProfileImageService profileImageService;

    @PostMapping("/upload")
    public String upload(@ModelAttribute ProfileImageDto profileImageDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        profileImageService.upload(profileImageDto, userDetails.getUsername());

        return "redirect:/user/mypage";
    }
}
