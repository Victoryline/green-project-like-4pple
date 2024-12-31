package org.example.restserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.restserver.utils.ConvertTokenUtil;
import org.example.restserver.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2024-12-29 by 황승현
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {
    private final JwtUtil jwtUtil;
    @GetMapping("/role")
    public String role(HttpServletRequest request) {
        String token = null;

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            token = ConvertTokenUtil.bearerExcludeToken(header);
        }

        return token != null && !token.equals("null") ? jwtUtil.getUserRole(token) : "";
    }
}

