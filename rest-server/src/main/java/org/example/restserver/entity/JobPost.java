package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "work_code", length = 20)
    private String workCode;

    @Column(name = "job_history")
    private int jobHistory;

    @Column(name = "job_salary")
    private int jobSalary;

    @Column(name = "education_code", length = 20)
    private String educationCode;

    @Column(name = "job_rank_code", length = 20)
    private String jobRankCode;

    @Column(name = "work_type_code", length = 20)
    private String workTypeCode;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "work_condition", columnDefinition = "TEXT")
    private String workCondition;

    @Column(name = "process")
    private int process;

    @Column(name = "method", columnDefinition = "TEXT")
    private String method;

    @Column(name = "add_notice", columnDefinition = "TEXT")
    private String addNotice;

    @Column(name = "manager_name", length = 50)
    private String managerName;

    @Column(name = "manager_phone", length = 20)
    private String managerPhone;

    @Column(name = "manager_email", length = 100)
    private String managerEmail;

    @Column(name = "end_yn", nullable = false, length = 1)
    private String endYn;

    @ToString.Exclude
    @OneToMany(mappedBy = "jobPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JobPostSkill> jobPostSkills;
}
