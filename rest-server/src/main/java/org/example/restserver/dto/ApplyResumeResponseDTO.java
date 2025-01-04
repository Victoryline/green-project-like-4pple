package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResumeResponseDTO {
    int jobPostNo;
    String name;
    Date birth;
    String gender;
    int age;
    int resumeNo;
    String title;
    Instant applyResumeDate;
    String companyId;
    Character passYn;
}

