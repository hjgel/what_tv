package promaxject.what_tv.user.controller;//package com.group.what_tv.user.controller;
//
//import com.group.what_tv.user.dto.UserDto;
//import com.group.what_tv.user.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class UserIndexController {
//    private final UserService userService;
//
//    public UserIndexController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/auth/join")
//    public String join() {return "/user/user-join";}
//
//    @PostMapping("/auth/joinProc")
//    public String joinProc(UserDto userDto) {
//        userService.join(userDto);
//
//        return "redirect:/auth/login";
//    }
//
//    @GetMapping("/auth/login")
//    public String login() {return "/user/user-login";}
//}
