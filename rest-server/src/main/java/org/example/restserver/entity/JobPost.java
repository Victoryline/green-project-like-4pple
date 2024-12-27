package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_job_post")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_no", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "work_code", nullable = false, length = 20)
    private String workCode;

    @Column(name = "job_history")
    private Integer jobHistory;

    @Column(name = "job_salary")
    private Integer jobSalary;

    @Column(name = "education_code", nullable = false, length = 20)
    private String educationCode;

    @Column(name = "job_rank_code", nullable = false, length = 20)
    private String jobRankCode;

    @Column(name = "work_type_code", nullable = false, length = 20)
    private String workTypeCode;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Lob
    @Column(name = "work_condition")
    private String workCondition;

    @Column(name = "process")
    private Integer process;

    @Lob
    @Column(name = "method")
    private String method;

    @Lob
    @Column(name = "add_notice")
    private String addNotice;

    @Column(name = "manager_name", length = 50)
    private String managerName;

    @Column(name = "manager_phone", length = 20)
    private String managerPhone;

    @Column(name = "manager_email", length = 100)
    private String managerEmail;

    @ColumnDefault("'N'")
    @Column(name = "end_yn")
    private Character endYn;

}