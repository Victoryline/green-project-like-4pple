package org.example.viewserver.controller;

import jakarta.servlet.http.HttpSession;
import org.example.viewserver.dto.SessionUserDto;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2025-01-01 by 황승현
 */
@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    @PostMapping
    public void setSessionUser(HttpSession session, @RequestBody SessionUserDto user) {
        session.setAttribute("user", user);
    }

    @DeleteMapping
    public void removeSessionUser(HttpSession session) {
        session.removeAttribute("user");
    }
}
