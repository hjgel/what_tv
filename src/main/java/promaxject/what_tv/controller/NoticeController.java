package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import promaxject.what_tv.domain.Notice;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.service.NoticeService;
import promaxject.what_tv.service.user.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    private final UserService userService;

    @GetMapping("/list")
    public String getAllNotices(Model model) {
        List<Notice> notices = noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "notice_list"; // 공지사항 목록 화면
    }

    // 공지사항 작성 (관리자만 접근 가능)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/create")
    public String createNoticeForm() {
        return "notice_create"; // 공지사항 작성 폼
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createNotice(@RequestParam("title") String title, @RequestParam("content") String content , Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        noticeService.createNotice(title, siteUser, content);
        return "redirect:/notice/list"; // 공지사항 목록으로 리디렉션
    }



    // 공지사항 수정 (관리자만 접근 가능)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String editNoticeForm(@PathVariable("id") Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice/edit"; // 공지사항 수정 폼
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit/{id}")
    public String editNotice(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content, Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        noticeService.updateNotice(id, title,  siteUser, content);
        return "redirect:/notice/list"; // 공지사항 목록으로 리디렉션
    }

    // 공지사항 상세 보기 (모든 사용자)
    @GetMapping("/view/{id}")
    public String viewNotice(@PathVariable("id") Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice_view"; // 공지사항 상세보기 화면
    }

}
