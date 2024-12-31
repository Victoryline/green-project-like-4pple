package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_job_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_no")
    private int jobPostNo;

    @Column(name = "username")
    private String username;

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
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "jobPost",  cascade = CascadeType.ALL)
    private List<JobPostSkill> jobPostSkills = new ArrayList<>();

//    @OneToMany(mappedBy = "company")
//    private List<JobPost> jobPosts;
//
//    @ManyToOne(fetch = FetchType.EAGER)  // 즉시 로딩 (EAGER)
//    @JoinColumn(name = "username")
//    private Company companyusername;
}
