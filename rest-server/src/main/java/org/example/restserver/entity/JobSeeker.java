package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobPost
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@Entity
@Table(name = "tbl_job_seeker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {

    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

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
