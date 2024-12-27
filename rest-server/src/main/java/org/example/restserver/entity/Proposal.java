package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_no", nullable = false)
    private Integer id;

    @Column(name = "company_id", nullable = false, length = 20)
    private String companyId;

    @Column(name = "job_seeker_id", nullable = false, length = 20)
    private String jobSeekerId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("current_timestamp()")
    @Column(name = "send_date", nullable = false)
    private Instant sendDate;

    @Column(name = "read_date")
    private Instant readDate;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;
}