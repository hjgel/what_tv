package promaxject.what_tv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
//    public String root() {
//        return "redirect:/question/list";
//    }
    public String root() {
//        return "redirect:/post/list";
        return "main_page";
    }
}
