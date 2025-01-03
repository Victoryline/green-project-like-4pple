package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : MilitaryRequestDto
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
public class MilitaryRequestDto {

    private String militaryCode;
    private String codeName;
    private String enlistDate;
    private String releaseDate;

}
