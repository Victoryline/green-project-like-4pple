package org.example.restserver.controller;

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
    public String login(@RequestBody UserRequestDto userRequestDto) {
        return userService.login(userRequestDto);
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
