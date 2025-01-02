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

    private int proposalNo; // 제안서 번호
    private String companyId; // 회사명
    private String userId; // 구직자 명
    private String title; // 제안서 제목
    private String content; // 제안서 내용
    private LocalDateTime sendTime; // 제안서 보낸 시간
    private LocalDateTime readTime; // 제안서 읽은 시간
    private String phone; // 회사 담당자 번호
    private String email; // 회사 담당자 이메일
}
