package org.example.restserver.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.dto.UserResponseDto;
import org.example.restserver.service.UserService;
import org.example.restserver.utils.ConvertTokenUtil;
import org.example.restserver.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final JwtUtil jwtUtil;

    @GetMapping
    public UserResponseDto getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = ConvertTokenUtil.bearerExcludeToken(token);
        String username = jwtUtil.getUsername(token);

        return userService.getUser(username);
    }


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        Map<String, Object> map = userService.login(userRequestDto);
        String token = map.get("token").toString();
        Cookie cookie = new Cookie("token", token);
        // 일주일
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Authorization", token);

        return map;
    }

    @PostMapping("/register")
    public int register(@RequestBody UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);
        return userService.register(userRequestDto);
    }

    @PutMapping("/update/{username}")
    public int update(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.update(username, userRequestDto);
    }

    @GetMapping("/check-duplication-username")
    public boolean checkDuplicationUsername(@RequestParam String username) {
        return userService.checkDuplicationUsername(username);
    }
}

