package org.example.restserver.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.BoardUserDto;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.dto.UserResponseDto;
import org.example.restserver.dto.WeeklyRegisterUsersDto;
import org.example.restserver.repository.UserRepository;
import org.example.restserver.service.UserService;
import org.example.restserver.utils.ConvertTokenUtil;
import org.example.restserver.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
//        response.setHeader("Authorization", token);

        return map;
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "로그아웃 완료";
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

    @GetMapping("/weekly-register-users-data")
    public List<WeeklyRegisterUsersDto> getWeeklyRegisterUsersData() {
        return userService.getWeeklyRegisterUsers();
    }

    @GetMapping("/role-user")
    public List<UserResponseDto> getRoleUser(@RequestParam String role) {
        return userRepository.findByRoleOrderByDeleteYnAndName(role).stream()
                .map((user) -> modelMapper.map(user, BoardUserDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/delete-yn/{username}")
    public int setDeleteYn(@PathVariable String username, @RequestParam String deleteYn) {
        return userRepository.updateDeleteYnByUsername(username, deleteYn);
    }
}
