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

    private String title;         // 공고 제목
    private String companyName;   // 회사명
    private String skills;        // 기술 스택
    private String companyAddress; // 회사 주소
    private Integer jobHistory;
    private byte[] profileImage;

}
