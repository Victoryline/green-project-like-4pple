package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_job_seeker")
public class JobSeeker {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "address_detail", length = 100)
    private String addressDetail;

    @Column(name = "zone_code", length = 5)
    private String zoneCode;
}
