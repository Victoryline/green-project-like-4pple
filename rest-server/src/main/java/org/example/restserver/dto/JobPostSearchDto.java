package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobPostSearchDto
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class JobPostSearchDto {
    private String title;           // 공고 제목
    private String companyName;     // 회사명
    private String skills;          // 기술 스택
    private String companyAddress;  // 회사 주소
    private Integer jobHistory;     // 경력
    private String educationCode;   // 학력 코드
    private String jobRankCode;     // 직급 코드
    private String workTypeCode;    // 근무 형태 코드
    private String profileImage;    // 프로필 이미지 (Base64 문자열)
    private LocalDate endDate;      // 마감일자
}