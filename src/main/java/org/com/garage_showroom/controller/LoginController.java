package org.com.garage_showroom.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        model.addAttribute("username", auth.getName());
        return "index"; // index.html
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // admin.html
    }
}
