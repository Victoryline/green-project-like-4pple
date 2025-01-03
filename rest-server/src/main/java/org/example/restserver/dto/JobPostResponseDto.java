package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class JobPostResponseDto {
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
    private byte[] profileImage;


}
