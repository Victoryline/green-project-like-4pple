package org.example.viewserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2024-12-28 by 황승현
 */
@Controller
@RequestMapping("/users/{role}")
public class UserController {

    @GetMapping("/register")
    public String register(@PathVariable String role) {
        return returnPage(role, "register-form") ;
    }

    private String returnPage(String role, String pageName) {
        return "/" + role + "/" + pageName;
    }
}
