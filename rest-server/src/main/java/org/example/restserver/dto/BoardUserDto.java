package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : BoardUserDto
 * author         : 황승현
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        황승현       최초 생성
 */
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUserDto extends UserResponseDto {
    private Instant createDate;
    private Instant updateDate;
    private String deleteYn;
}
