package org.example.viewserver.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : org.example.viewserver.dto
 * fileName       : ProposalDto
 * author         : 이동하
 * date           : 2024-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-28        이동하       최초 생성
 */

@Data
public class ProposalDto {

    private int proposalNo;
    private String companyId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime sendTime;
    private LocalDateTime readTime;
    private String phone;
    private String email;
}
