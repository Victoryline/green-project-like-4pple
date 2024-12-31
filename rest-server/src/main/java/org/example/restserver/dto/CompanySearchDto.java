package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * packageName    : org.example.restserver.entity
 * fileName       : UserCompanyDto
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanySearchDto {

    private String name;
    private String address;
    private LocalDate birth;
    private Double averageScore; // 기업 별점

}
