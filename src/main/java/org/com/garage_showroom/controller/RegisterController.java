package org.com.garage_showroom.controller;

import lombok.RequiredArgsConstructor;
import org.com.garage_showroom.dto.request.RegisterRequest;
import org.com.garage_showroom.service.UserService;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("req", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("req") RegisterRequest req,
                             BindingResult binding,
                             Model model) {
        if (binding.hasErrors()) {
            return "register";
        }
        try {
            userService.registerCustomer(req);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("registerError", ex.getMessage());
            return "register";
        }
        return "redirect:/login?registered";
    }
}
