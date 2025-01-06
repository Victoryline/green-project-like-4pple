package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : BookmarkResponseDto
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
public class BookmarkResponseDto {

    private String companyName;
    private String companyAddress;
    private byte[] companyImage;
}
