package org.example.restserver.service;

import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.dto.UserResponseDto;

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
    UserResponseDto login (UserRequestDto userRequestDto);
}
