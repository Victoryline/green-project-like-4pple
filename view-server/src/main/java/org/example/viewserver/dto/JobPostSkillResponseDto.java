package org.example.viewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobPostSkillResponseDto
 * author         : 이동하
 * date           : 2025-01-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-05        이동하       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostSkillResponseDto {
    private Integer jobPostNo;
    private String skillCode;

}
