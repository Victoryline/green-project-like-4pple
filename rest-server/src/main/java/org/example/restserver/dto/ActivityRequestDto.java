package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : ActivityReqeustDto
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequestDto {

    private String activityType;
    private String codeName;
    private String activityCenterName;
    private String activityContent;
    private String startDate;
    private String endDate;

}
