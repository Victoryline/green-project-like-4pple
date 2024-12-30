package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : PortfolioRequestDto
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRequestDto {

    private String portfolioFilename;
    private String portfolioLink;

}
