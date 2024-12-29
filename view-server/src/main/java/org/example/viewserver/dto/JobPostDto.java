package org.example.viewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobPostDto
 * author         : 박미정
 * date           : 24. 12. 26.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 26.        박미정      최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostDto {

    private String id;
    private String username;
    private String title;
    private String workCode;
    private Integer jobHistory;
    private Integer jobSalary;
    private String educationCode;
    private String jobRankCode;
    private String workTypeCode;
    private Instant startDate;
    private Instant endDate;
    private String content;
    private String workCondition;
    private Integer process;
    private String method;
    private String addNotice;
    private String managerName;
    private String managerPhone;
    private String managerEmail;
    private Character endYn;
 //   private String fileName;
    private String benefit;
    private String skillCode;



}
