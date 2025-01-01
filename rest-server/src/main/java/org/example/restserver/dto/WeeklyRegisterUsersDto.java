package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : WeeklyRegistUsers
 * author         : 황승현
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        황승현       최초 생성
 */
@Data
@AllArgsConstructor
public class WeeklyRegisterUsersDto {
    private String date;
    private int jobSeekers;
    private int companies;
}
