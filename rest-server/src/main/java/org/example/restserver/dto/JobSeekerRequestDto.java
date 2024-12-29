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
public class JobSeekerRequestDto {
    private String username;
    private String phone;
    private String email;
    private Character gender;
    private LocalDate birth;
    private String address;
    private String addressDetail;
    private String zoneCode;
}