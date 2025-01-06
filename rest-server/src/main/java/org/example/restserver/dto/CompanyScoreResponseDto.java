package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restserver.entity.CompanyScore;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : CompanyScoreResponse
 * author         : 이동하
 * date           : 2025-01-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-03        이동하       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyScoreResponseDto {
    private boolean success;
    private Double averageScore;
}
