package com.trendy.login;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserRepository userRepository;

    // 생성자 주입 방식
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        String email = oAuth2User.getAttribute("email");
        User user = userRepository.findByEmail(email); // 에러 해결

        model.addAttribute("user", user);
        return "mypage";
    }
}
