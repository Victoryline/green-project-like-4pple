package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
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