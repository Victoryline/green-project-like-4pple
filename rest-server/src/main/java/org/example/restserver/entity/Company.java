package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.restserver.entity.User;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "business_number", nullable = false, length = 10)
    private String businessNumber;

    @Lob
    @Column(name = "info")
    private String info;

    @Column(name = "profile_image")
    private byte[] profileImage;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "address_detail", nullable = false, length = 50)
    private String addressDetail;

    @Column(name = "zonecode", nullable = false, length = 5)
    private String zoneCode;

    @Column(name = "contact", nullable = false, length = 20)
    private String contact;

    @Column(name = "website", length = 200)
    private String website;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "employee", nullable = false)
    private Integer employee;
}
