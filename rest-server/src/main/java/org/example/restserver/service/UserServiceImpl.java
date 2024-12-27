package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.dto.UserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName    : org.example.restserver.service
 * fileName       : UserServiceImpl
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponseDto login(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));

        return null;
    }
}
