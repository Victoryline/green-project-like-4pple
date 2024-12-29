package org.example.restserver.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobSeeker;

import java.time.Instant;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : UserRequestDto
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
public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private String role;

    JobSeekerRequestDto jobSeeker;
    CompanyRequestDto company;
}
