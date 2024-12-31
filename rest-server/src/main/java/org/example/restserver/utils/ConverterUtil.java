package org.example.restserver.utils;

import org.example.restserver.dto.*;
import org.example.restserver.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * packageName    : org.example.restserver.utils
 * fileName       : ConverterUtil
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Component
public class ConverterUtil {

    public Education dtoToEntity(Resume res, EducationRequestDto education){

        Education educationEntity = new Education();

        educationEntity.setTblResume(res);
        educationEntity.setEducationCode(education.getEducationCode());
        educationEntity.setSchoolName(education.getSchoolName());
        educationEntity.setSpecialty(education.getSpecialty());
        educationEntity.setEnterDate(education.getEnterDate());
        educationEntity.setGraduateDate(education.getGraduateDate());

        return educationEntity;
    }

    public Military militaryToEntity(Resume res, MilitaryRequestDto military){
        Military militaryEntity = new Military();

        militaryEntity.setTblResume(res);
        militaryEntity.setMilitaryCode(military.getMilitaryCode());
        militaryEntity.setEnlistDate(military.getEnlistDate());
        militaryEntity.setReleaseDate(military.getReleaseDate());

        return militaryEntity;
    }

    public Activity activityToEntity(Resume res, ActivityRequestDto activity) {

        Activity activityEntity = new Activity();
        ActivityId activityId = new ActivityId();

        activityId.setActivityType(activity.getActivityType());
        activityEntity.setId(activityId);
        activityEntity.setResumeNo(res);
        activityEntity.setActivityCenterName(activity.getActivityCenterName());
        activityEntity.setActivityContent(activity.getActivityContent());
        activityEntity.setStartDate(activity.getStartDate());
        activityEntity.setEndDate(activity.getEndDate());

        return activityEntity;

    }

    public License licenseToEntity(Resume res, LicenseRequestDto license){

        License licenseEntity = new License();
        LicenseId licenseId = new LicenseId();

        licenseId.setLicenseName(license.getLicenseName());

        licenseEntity.setId(licenseId);
        licenseEntity.setResumeNo(res);
        licenseEntity.setLicenseCenterName(license.getLicenseCenterName());
        licenseEntity.setPassDate(license.getPassDate());

        return licenseEntity;
    }

    public ResumeSkill skillToEntity(Resume res, SkillCodeRequestDto skill){

        ResumeSkill resumeSkill = new ResumeSkill();
        ResumeSkillId resumeSkillId = new ResumeSkillId();

        resumeSkillId.setSkillCode(skill.getSkillCode());

        resumeSkill.setResumeNo(res);
        resumeSkill.setId(resumeSkillId);

        return resumeSkill;
    }

    public Potfolio potfolioToEntity(Resume res, PortfolioRequestDto portfolio){

        Potfolio portfolioEntity = new Potfolio();
        PotfolioId portfolioId = new PotfolioId();

        portfolioId.setPotfolioFilename(portfolio.getPortfolioFilename());

        portfolioEntity.setResumeNo(res);
        portfolioEntity.setId(portfolioId);
        portfolioEntity.setPortfolioLink(portfolioEntity.getPortfolioLink());

        return portfolioEntity;

    }

    public Introduce introduceToEntity(Resume res, IntroduceRequestDto introduce){

        Introduce intro = new Introduce();
        IntroduceId introId = new IntroduceId();

        introId.setTitle(introduce.getTitle());

        intro.setId(introId);
        intro.setResumeNo(res);
        intro.setContent(introduce.getContent());
        intro.setOrd(introduce.getOrd());

        return intro;

    }

}
