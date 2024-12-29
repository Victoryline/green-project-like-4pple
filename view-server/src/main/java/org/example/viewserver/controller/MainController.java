package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.common.ApiResponse;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2024-12-29 by 황승현
 */
@Controller
@RequiredArgsConstructor
public class MainController {
    private final WebClientManager webClientManager;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String role, Model model) {
        model.addAttribute("role", role);

        return "login";
    }

    @GetMapping
    public String main() {
        String role = webClientManager.get("/api/token/role").getBody().toString();

        return switch (role) {
            case "ROLE_ADMIN" -> "admin-main";
            case "ROLE_COMPANY" -> "company-main";
            default -> "main";
        };
    }
}
