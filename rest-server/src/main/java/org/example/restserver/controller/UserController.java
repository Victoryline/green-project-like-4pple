package org.example.restserver.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.entity.JobSeeker;
import org.example.restserver.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : UserController
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        String token = userService.login(userRequestDto);
        Cookie cookie = new Cookie("token", token);
        // 일주일
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Authorization", token);

        return token;
    }

    @PostMapping("/register")
    public int register(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        return userService.register(userRequestDto);
    }

    @GetMapping("/check-duplication-username")
    public boolean checkDuplicationUsername(@RequestParam String username) {
        return userService.checkDuplicationUsername(username);
    }
}
