package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restserver.entity.JobPostSkill;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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

        private String username;
        private String title;
        private String workCode;
        private Integer jobHistory;
        private Integer jobSalary;
        private String educationCode;
        private String jobRankCode;
        private String workTypeCode;
        private LocalDate startDate;
        private LocalDate endDate;
        private String content;
        private String workCondition;
        private Integer process;
        private String method;
        private String addNotice;
        private String managerName;
        private String managerPhone;
        private String managerEmail;
        private Character endYn;
        private List<String> benefitContent = Collections.emptyList();
        private List<String>  jobPostSkills= Collections.emptyList();

        //company
        private String companyUsername;
        private String address;
        public JobPostDto(String username, String title, String workCode, Integer jobHistory, Integer jobSalary, String educationCode, String jobRankCode, String workTypeCode, LocalDate startDate, LocalDate endDate, String content, String workCondition, Integer process, String method, String addNotice, String managerName, String managerPhone, String managerEmail, Character endYn) {
        }

}
