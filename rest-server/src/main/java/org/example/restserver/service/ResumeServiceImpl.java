package org.example.restserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.ognl.Evaluation;
import org.example.restserver.dto.*;
import org.example.restserver.entity.*;
import org.example.restserver.repository.*;
import org.example.restserver.utils.ConverterUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : ResumeServiceImpl
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final MilitaryRepository militaryRepository;
    private final ActivityRepository activityRepository;
    private final LicenseRepository licenseRepository;
    private final ResumeSkillRepository resumeSkillRepository;
    private final PortfolioRepository portfolioRepository;
    private final IntroduceRepository introduceRepository;

    private final ConverterUtil converterUtil;

    @Override
    @Transactional
    public int postResume(ResumeRequestDto resumeDto) {
        User user = new User();
        user.setUsername(resumeDto.getUsername());

        Resume resume = new Resume();

        resume.setUsername(user);
        resume.setTitle(resumeDto.getRTitle());
        resume.setImage(resumeDto.getImage());
        resume.setWishArea(resumeDto.getWishArea());
        resume.setWishSalary(resumeDto.getWishSalary());
        resume.setWishTime(resumeDto.getWishTime());
        resume.setWorkCode(resumeDto.getWorkCode());

        Resume res = resumeRepository.save(resume);

        List<EducationRequestDto> educations = resumeDto.getEducation();
        for(EducationRequestDto edu : educations){
            Education education = converterUtil.dtoToEntity(res, edu);
            educationRepository.save(education);
        }

        MilitaryRequestDto military = resumeDto.getMilitary();
        if(military!= null){
            Military militaryEntity = converterUtil.militaryToEntity(res, military);
            militaryRepository.save(militaryEntity);
        }

        List<ActivityRequestDto> activities = resumeDto.getActivity();
        for(ActivityRequestDto act : activities){
            Activity activity = converterUtil.activityToEntity(res, act);
            activityRepository.save(activity);
        }

        List<LicenseRequestDto> licenses = resumeDto.getLicenses();
        for(LicenseRequestDto lic : licenses){
            License license = converterUtil.licenseToEntity(res, lic);
            licenseRepository.save(license);
        }

        List<SkillCodeRequestDto> skills = resumeDto.getSkill();
        for(SkillCodeRequestDto skill : skills){
            ResumeSkill resumeSkill = converterUtil.skillToEntity(res, skill);
            resumeSkillRepository.save(resumeSkill);
        }

        List<PortfolioRequestDto> portfolios = resumeDto.getPortfolio();
        for(PortfolioRequestDto port : portfolios){
            Potfolio portfolio = converterUtil.potfolioToEntity(res, port);
            portfolioRepository.save(portfolio);
        }

        List<IntroduceRequestDto> introduceRequests = resumeDto.getIntroduce();
        for(IntroduceRequestDto intro : introduceRequests){
            Introduce introduce = converterUtil.introduceToEntity(res, intro);
            introduceRepository.save(introduce);
        }

        return 1;
    }

}
