package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String username;
    private String name;
    private String role;
}
