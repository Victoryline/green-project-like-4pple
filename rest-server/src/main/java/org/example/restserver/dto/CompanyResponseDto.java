package org.example.restserver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CompanyResponseDto extends UserResponseDto {
  private String businessNumber;
  private String info;
  private String profileImage;

  private String address;
  private String addressDetail;
  private String zonecode;
  private String contact;
  private String website;
  private String email;
  private LocalDate birth;
  private int employee;
  private String name;

  private byte[] image;
}
