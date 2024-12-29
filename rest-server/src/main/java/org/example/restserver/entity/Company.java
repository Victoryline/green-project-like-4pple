package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "tbl_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(name = "business_number", nullable = false, length = 10)
    private String businessNumber;

    @Column(name = "info", columnDefinition = "TEXT")
    private String info;

    @Column(name = "profile_image")
    @Lob
    private byte[] profileImage;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "address_detail", length = 50)
    private String addressDetail;

    @Column(name = "zone_code", length = 5)
    private String zoneCode;

    @Column(name = "contact", length = 20)
    private String contact;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "employee")
    private int employee;
}