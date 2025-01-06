package org.example.restserver.dto;

import lombok.*;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : LikeRequestDto
 * author         : 김재홍
 * date           : 25. 1. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 6.        김재홍       최초 생성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeRequestDto {

    private String username;
    private String seeker;
    private String likeType;

}
