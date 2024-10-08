package promaxject.what_tv.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/main")
    @ResponseBody
    public String index() {
            return "안녕하세요. 어쩔 TV에 오신 것을 환영합니다.";
    }

    @GetMapping("/")
    public String root() {
        // "/"만 들어오면 /question/list로 리다이렉트 하라는 것이다.
        return "redirect:/question/list";
    }
}
