package org.example.restserver.controller;

import org.example.restserver.dto.UserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
    @PostMapping("/login")
    public void login(UserRequestDto userRequestDto) {

    }
}
