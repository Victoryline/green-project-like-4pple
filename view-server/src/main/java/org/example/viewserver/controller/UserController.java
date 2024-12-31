package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2024-12-28 by 황승현
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{role}")
public class UserController {
    private final WebClientManager webClientManager;

    @GetMapping("/register")
    public String register(@PathVariable String role) {
        return returnPage(role, "register-form"); 
    }

    @GetMapping("/edit")
    public String edit(@PathVariable String role, Model model) {
        var user = webClientManager.get("/api/v1/users").getBody();
        System.out.println(user);
        model.addAttribute("user", user);
        return returnPage(role, "edit-form");
    }

    private String returnPage(String role, String pageName) {
        return "/" + role + "/" + pageName;
    }
}
