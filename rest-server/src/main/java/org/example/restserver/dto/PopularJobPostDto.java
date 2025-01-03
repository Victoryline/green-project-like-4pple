package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : PoplarJobPostDto
 * author         : 황승현
 * date           : 2025-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-03        황승현       최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularJobPostDto {
    private int jobPostNo;
    private String username;
    private String companyName;
    private String title;
    private String workCode;
    private Integer jobHistory;
    private Integer jobSalary;
    private LocalDate startDate;
    private LocalDate endDate;
    private String workCondition;
    private Character endYn;
    private String jobPostSkills;
    private String address;
    private String profileImage;
    private String day;
}
