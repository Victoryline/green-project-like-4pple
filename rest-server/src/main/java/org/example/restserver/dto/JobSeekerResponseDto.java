package org.example.restserver.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.restserver.entity.JobSeeker;

import java.time.LocalDate;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobSeekerResponseDto
 * author         : 황승현
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        황승현       최초 생성
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class JobSeekerResponseDto extends UserResponseDto {
    private String phone;
    private String email;
    private String gender;
    private LocalDate birth;
    private String address;
    private String addressDetail;
    private String zoneCode;
}
