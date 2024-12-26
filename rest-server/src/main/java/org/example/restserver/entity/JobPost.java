package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : org.example.restserver.entity
 * fileName       : JobPost
 * author         : 이동하
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        이동하       최초 생성
 */
@Entity
@Table(name = "tbl_job_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_no")
    private int jobPostNo;
    @Column(name = "username")
    private String username;
    @Column(name = "title")
    private String title;
    @Column(name = "work_code")
    private String workCode;
    @Column(name = "job_history")
    private int jobHistory;
    @Column(name = "job_salary")
    private int jobSalary;
    @Column(name = "education_code")
    private String educationCode;
    @Column(name = "job_rank_code")
    private String jobRankCode;
    @Column(name = "work_type_code")
    private String workTypeCode;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "content")
    private String content;
    @Column(name = "work_condition")
    private String workCondition;
    @Column(name = "process")
    private int process;
    @Column(name = "method")
    private String method;
    @Column(name = "add_notice")
    private String addNotice;
    @Column(name = "manager_name")
    private String managerName;
    @Column(name = "manager_phone")
    private String managerPhone;
    @Column(name = "manager_email")
    private String managerEmail;
    @Column(name = "end_yn")
    private String endYn;

    @OneToMany(mappedBy = "jobPost")
    private List<JobSkill> skill;
}
