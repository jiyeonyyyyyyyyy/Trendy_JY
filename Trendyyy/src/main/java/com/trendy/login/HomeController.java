package com.trendy.login;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 메인 페이지
    @GetMapping("/")
    public String index() {
        return "index"; // index.html (Thymeleaf)
    }

    // 로그인 성공 후 이동
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        if (oAuth2User != null) {
            model.addAttribute("attributes", oAuth2User.getAttributes());
        }
        return "loginSuccess"; 
    }

    // 로그인 실패 시 이동
    @GetMapping("/loginFailure")
    public String loginFailure(Model model) {
        model.addAttribute("errorMsg", "로그인 실패!");
        return "loginFailure"; 
    }
}