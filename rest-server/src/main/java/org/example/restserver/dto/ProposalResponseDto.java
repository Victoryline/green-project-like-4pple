package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : ProposalResponse
 * author         : 이동하
 * date           : 2025-01-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-01        이동하       최초 생성
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalResponseDto {
    private Integer id;
    private String companyId;
    private String userId;
    private String title;
    private String content;
    private Instant sendDate;
    private Instant readDate;
}
