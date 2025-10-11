package org.com.garage_showroom.controller;

import lombok.RequiredArgsConstructor;
import org.com.garage_showroom.dto.request.RegisterRequest;
import org.com.garage_showroom.dto.request.VerifyOtpRequest;
import org.com.garage_showroom.service.RegistrationService;
import org.com.garage_showroom.service.UserService;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String registerForm() { return "register"; }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute RegisterRequest req, RedirectAttributes ra) {
        try {
            registrationService.register(req);
            ra.addFlashAttribute("msg", "Đã gửi OTP đến email của bạn. Vui lòng kiểm tra hộp thư.");
            return "redirect:/verify-otp?email=" + UriUtils.encode(req.getEmail(), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/verify-otp")
    public String verifyForm(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String doVerify(@ModelAttribute VerifyOtpRequest req, RedirectAttributes ra) {
        try {
            registrationService.verifyRegisterOtp(req);
            ra.addFlashAttribute("msg", "Xác nhận thành công! Bạn có thể đăng nhập.");
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/verify-otp?email=" + UriUtils.encode(req.email(), StandardCharsets.UTF_8);
        }
    }

    // (tuỳ chọn) gửi lại OTP
    @PostMapping("/resend-otp")
    public String resend(@RequestParam String email, RedirectAttributes ra) {
        // Tái sử dụng register() nhưng không đổi mật khẩu: tuỳ bạn thiết kế.
        return "redirect:/verify-otp?email=" + UriUtils.encode(email, StandardCharsets.UTF_8);
    }
}
