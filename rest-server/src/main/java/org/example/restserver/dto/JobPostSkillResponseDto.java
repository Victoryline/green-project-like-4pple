package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restserver.entity.JobPost;
import org.example.restserver.entity.JobPostSkill;

import java.time.LocalDate;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : JobPostSkillResponseDto
 * author         : 이동하
 * date           : 2025-01-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-05        이동하       최초 생성
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostSkillResponseDto {
    private int jobPostNo;
    private String username;
    private String companyName;
    private String title;
    private String workCode;
    private Integer jobHistory;
    private Integer jobSalary;
    private LocalDate startDate;
    private LocalDate endDate;
    private String workCondition;
    private Character endYn;
    private String jobPostSkills;
    private String address;
    private byte[] profileImage;

    public JobPostSkillResponseDto(JobPostSkill jobPostSkill, String companyName) {
        this.jobPostNo = jobPostSkill.getId().getJobPostNo();
        this.jobPostSkills = jobPostSkill.getId().getSkillCode();

        if (jobPostSkill.getJobPost() != null) {
            this.title = jobPostSkill.getJobPost().getTitle();
            this.workCode = jobPostSkill.getJobPost().getWorkCode();
            this.jobHistory = jobPostSkill.getJobPost().getJobHistory();
            this.jobSalary = jobPostSkill.getJobPost().getJobSalary();
            this.startDate = jobPostSkill.getJobPost().getStartDate();
            this.endDate = jobPostSkill.getJobPost().getEndDate();
            this.workCondition = jobPostSkill.getJobPost().getWorkCondition();
            this.endYn = jobPostSkill.getJobPost().getEndYn();

            if (jobPostSkill.getJobPost().getCompany() != null) {
                this.username = jobPostSkill.getJobPost().getCompany().getUsername();
                this.companyName = companyName;
                this.address = jobPostSkill.getJobPost().getCompany().getAddress();
                this.profileImage = jobPostSkill.getJobPost().getCompany().getProfileImage();
            } else {
                this.username = "회사명 없음";
                this.companyName = "회사명 없음";
                this.address = "주소 없음";
            }
        } else {
            this.title = "제목 없음";
            this.workCode = "작업 코드 없음";
            this.jobHistory = 0;
            this.jobSalary = 0;
            this.startDate = null;
            this.endDate = null;
            this.workCondition = "작업 조건 없음";
            this.endYn = 'N';
        }
    }

    public JobPostSkillResponseDto(JobPost jobPost) {
    }
}
