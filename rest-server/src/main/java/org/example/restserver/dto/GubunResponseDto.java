package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : GubunResponseDto
 * author         : 김재홍
 * date           : 24. 12. 31.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 31.        김재홍       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GubunResponseDto {

    private String gubunCode;
    private String code;
    private String name;

}
