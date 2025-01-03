package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.restserver.entity.JobSeeker;

import java.util.List;

/**
 * packageName    : org.example.restserver.dto
 * fileName       : ResumeDto
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponseDto {

    private String resumeNo;

    private String username;

    private String title;

    private String image;

    private JobSeekerUserResponseDto user;

    //희망 조건
    private String wishArea;
    private Integer wishSalary;
    private String wishTime;
    private String workCode;
    //학력
    private EducationRequestDto educations;
    //병역
    private MilitaryRequestDto military;
    //경력
    private List<ActivityRequestDto> activities;
    //자격증
    private List<LicenseRequestDto> licenses;
    //기술스택
    private List<SkillCodeRequestDto> skills;
    //포트폴리오
    private List<PortfolioRequestDto> portfolios;
    //자소서
    private List<IntroduceRequestDto> introduces;

}
