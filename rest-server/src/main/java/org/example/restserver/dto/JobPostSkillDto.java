package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobPostSkillDto
 * author         : 이동하
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        이동하       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostSkillDto {
    private String username;
    private String title;
    private String workCode;
    private Integer jobHistory;
    private String educationCode;
    private String jobRankCode;
    private String workTypeCode;
    private LocalDate endDate;
    private List<String> skills; // 기술 스택 목록
}