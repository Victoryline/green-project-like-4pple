package org.example.viewserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.SessionUserDto;
import org.example.viewserver.utils.SessionUserManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2025-01-01 by 황승현
 */
@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionUserManager sessionUserManager;

    @PostMapping
    public void setSessionUser(HttpSession session, @RequestBody SessionUserDto user) {
        session.setAttribute("user", user);
    }

    @DeleteMapping
    public void removeSessionUser() {
        sessionUserManager.removeUser();
    }
}
