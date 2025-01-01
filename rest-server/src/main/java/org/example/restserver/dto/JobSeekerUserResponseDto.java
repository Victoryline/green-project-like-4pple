package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobSeekerUserDto
 * author         : 김재홍
 * date           : 24. 12. 31.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 31.        김재홍       최초 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerUserResponseDto {

    private String username;
    private String name;
    private String phone;
    private String email;
    private String birth;
    private String address;

}
