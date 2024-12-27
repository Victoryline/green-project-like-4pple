package org.example.restserver.dto;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : UserResponseDto
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
@Builder
@Data
public class UserResponseDto {
    private String username;
    private String password;
    private String role;
}
