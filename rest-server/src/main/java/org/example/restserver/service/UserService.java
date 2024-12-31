package org.example.restserver.service;

import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.dto.UserResponseDto;

import java.util.Map;

/**
 * packageName    : org.example.restserver.service
 * fileName       : UserService
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
public interface UserService {
    int register (UserRequestDto userRequestDto);
    Map<String, Object> login (UserRequestDto userRequestDto);

    boolean checkDuplicationUsername(String username);

    UserResponseDto getUser(String username);

    int update(String username,UserRequestDto userRequestDto);
}

