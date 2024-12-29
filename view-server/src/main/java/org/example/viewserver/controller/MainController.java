package org.example.viewserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2024-12-29 by 황승현
 */
@Controller
public class MainController {
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String role, Model model) {
        model.addAttribute("role", role);

        return "login";
    }

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        return "main";
    }
}
