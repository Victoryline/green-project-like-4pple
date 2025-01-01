package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_license")
public class License {
    @EmbeddedId
    private LicenseId id;

    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume resumeNo;

    @Column(name = "license_center_name", nullable = false, length = 50)
    private String licenseCenterName;

    @Column(name = "pass_date", nullable = false, length = 6)
    private String passDate;

}