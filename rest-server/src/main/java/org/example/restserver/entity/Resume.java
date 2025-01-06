package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_no", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "wish_area", length = 50)
    private String wishArea;

    @Column(name = "wish_salary")
    private Integer wishSalary;

    @Column(name = "wish_time", length = 20)
    private String wishTime;

    @Column(name = "work_code", length = 20)
    private String workCode;

    @Column(name = "offer_yn")
    private Character offerYn;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "modify_date")
    private Instant modifyDate;

    @ColumnDefault("0")
    @Column(name = "job_history")
    private Integer jobHistory;

    @Column(name = "is_primary")
    private String isPrimary;

    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = Instant.now();
        }
        if(offerYn == null) {
            offerYn = 'N';
        }
    }

    @PreUpdate
    public void preUpdate() {
        modifyDate = Instant.now();
    }



}