package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : CompanyScoreDto
 * author         : 이동하
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        이동하       최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyScoreDto {
    private String companyId;
    private String jobSeekerId;
    private Integer score;

}
