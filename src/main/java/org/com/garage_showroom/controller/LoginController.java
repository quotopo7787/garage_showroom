package org.com.garage_showroom.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {
    // Trang chủ: nếu muốn có dashboard, trả về "index" hoặc redirect
    @GetMapping("/")
    public String home() {
        return "index"; // tạo templates/index.html sau (hoặc đổi thành redirect:/dashboard)
    }

    // GET /login hiển thị form đăng nhập
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model,
                            Principal principal,
                            HttpServletRequest request) {

        // Nếu đã đăng nhập rồi thì quay về trang chủ
        if (principal != null) {
            return "redirect:/";
        }

        if (error != null) {
            Exception ex = (Exception) request.getSession()
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            String message = (ex != null && ex.getMessage() != null)
                    ? ex.getMessage()
                    : "Email hoặc mật khẩu không hợp lệ";
            model.addAttribute("errorMessage", message);
        }

        if (logout != null) {
            model.addAttribute("logoutMessage", "Bạn đã đăng xuất.");
        }

        return "login"; // render templates/login.html
    }

    // (tuỳ chọn) trang cấm truy cập
    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("errorMessage", "Bạn không có quyền truy cập trang này.");
        return "access-denied";
    }
}
