package promaxject.what_tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import promaxject.what_tv.domain.Notice;
import promaxject.what_tv.domain.Question;
import promaxject.what_tv.domain.SiteUser;
import promaxject.what_tv.form.NoticeForm;
import promaxject.what_tv.form.PostForm;
import promaxject.what_tv.service.NoticeService;
import promaxject.what_tv.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    private final UserService userService;

//    @GetMapping("/list")
//    public String getAllNotices(Model model) {
//        List<Notice> paging = noticeService.getAllNotices();
//        model.addAttribute("paging", paging);
//        return "notice_list"; // 공지사항 목록 화면
//    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Notice> paging = this.noticeService.getAllNotices(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "notice_list";
    }


    // 공지사항 작성 (관리자만 접근 가능)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/create")
    public String createNoticeForm(NoticeForm noticeForm) {
        return "notice_form"; // 공지사항 작성 폼
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createNotice(@Valid NoticeForm noticeForm, @RequestParam("title") String title, @RequestParam("content") String content , Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        noticeService.createNotice(noticeForm.getTitle(), siteUser, noticeForm.getContent());
        return "redirect:/notice/list"; // 공지사항 목록으로 리디렉션
    }


//    // 공지사항 수정 (관리자만 접근 가능)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/edit/{id}")
//    public String editNoticeForm(@PathVariable("id") Long id, Model model) {
//        Notice notice = noticeService.getNoticeById(id);
//        model.addAttribute("notice", notice);
//        return "notice/edit"; // 공지사항 수정 폼
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/edit/{id}")
//    public String editNotice(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content, Principal principal) {
//        SiteUser siteUser = this.userService.getUser(principal.getName());
//        noticeService.updateNotice(id, title,  siteUser, content);
//        return "redirect:/notice/list"; // 공지사항 목록으로 리디렉션
//    }

    // 공지사항 상세 보기 (모든 사용자)
    @GetMapping("/detail/{id}")
    public String viewNotice(@PathVariable("id") Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice_detail"; // 공지사항 상세보기 화면
    }

    // 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Long id) {
        Notice notice = this.noticeService.getNoticeById(id);
        if(!notice.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.noticeService.delete(notice);
        return "redirect:/notice/list";
    }

}
