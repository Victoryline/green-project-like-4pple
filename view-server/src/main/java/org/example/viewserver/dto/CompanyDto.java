package org.example.viewserver.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.example.viewserver.dto
 * fileName       : CompanyDto
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */
@Data
public class CompanyDto {

    private String username;
    private String businessNumber;
    private String info;
    private String profileImage;
    private String address;
    private String addressDetail;
    private String zoneCode;
    private String contact;
    private String website;
    private String email;
    private LocalDate birth;
    private Integer employee;
}
