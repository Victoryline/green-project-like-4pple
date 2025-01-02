package org.example.restserver.dto;

import lombok.Data;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : ProposalRequestDto
 * author         : 이동하
 * date           : 2025-01-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-01        이동하       최초 생성
 */

@Data
public class ProposalRequestDto {
    private String companyId;
    private String userId;
    private String title;
    private String content;
    private String phone;
    private String email;
}
