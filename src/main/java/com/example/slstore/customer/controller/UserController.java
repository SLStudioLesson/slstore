package com.example.slstore.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/customer/login")
    public String login() {
        return "customer/account/login-form";
    }

    @GetMapping("/mypage")
    public String myPage(Model model) {
        // ここから
        
        
        // ここまで
        return "customer/account/mypage";
    }

    @GetMapping("/mypage/detail")
    public String myPageDetail(Model model) {
        // ここに処理を追記する
        return "customer/account/mypage-detail";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "customer/account/privacy-policy";
    }
}
