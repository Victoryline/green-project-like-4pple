package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.*;
import org.example.restserver.entity.*;
import org.example.restserver.repository.*;
import org.example.restserver.utils.ConverterUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final EducationRepository educationRepository;
    private final MilitaryRepository militaryRepository;
    private final ActivityRepository activityRepository;
    private final LicenseRepository licenseRepository;
    private final ResumeSkillRepository resumeSkillRepository;
    private final PortfolioRepository portfolioRepository;
    private final IntroduceRepository introduceRepository;

    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;

    private final ConverterUtil converterUtil;

    @Override
    public int postResume(ResumeRequestDto resumeDto) {

        log.info(String.valueOf(resumeDto));

        Resume resume = new Resume();

        resume.setUsername(resumeDto.getUsername());
        resume.setTitle(resumeDto.getCaption());
        resume.setImage(resumeDto.getImage());
        resume.setWishArea(resumeDto.getWishArea());
        resume.setWishSalary(resumeDto.getWishSalary());
        resume.setWishTime(resumeDto.getWishTime());
        resume.setWorkCode(resumeDto.getWorkCode());

        Resume res = resumeRepository.save(resume);

        if(resumeDto.getEducations() != null){
            List<EducationRequestDto> educations = resumeDto.getEducations();
            for(EducationRequestDto edu : educations){
                Education education = converterUtil.dtoToEntity(res, edu);
                educationRepository.save(education);
            }
        }

        MilitaryRequestDto military = resumeDto.getMilitary();
        if(military!= null){
            Military militaryEntity = converterUtil.militaryToEntity(res, military);
            militaryRepository.save(militaryEntity);
        }

        if(resumeDto.getActivities() != null){
            List<ActivityRequestDto> activities = resumeDto.getActivities();
            for(ActivityRequestDto act : activities){
                Activity activity = converterUtil.activityToEntity(res, act);
                activityRepository.save(activity);
            }
        }

        if(resumeDto.getLicenses() != null){
            List<LicenseRequestDto> licenses = resumeDto.getLicenses();
            for(LicenseRequestDto lic : licenses){
                License license = converterUtil.licenseToEntity(res, lic);
                licenseRepository.save(license);
            }
        }

        if(resumeDto.getSkills() != null){
            List<SkillCodeRequestDto> skills = resumeDto.getSkills();
            for(SkillCodeRequestDto skill : skills){
                ResumeSkill resumeSkill = converterUtil.skillToEntity(res, skill);
                resumeSkillRepository.save(resumeSkill);
            }
        }

        if (resumeDto.getPortfolios() != null){
            List<PortfolioRequestDto> portfolios = resumeDto.getPortfolios();
            for(PortfolioRequestDto port : portfolios){
                Potfolio portfolio = converterUtil.potfolioToEntity(res, port);
                portfolioRepository.save(portfolio);
            }
        }

        if(resumeDto.getIntroduces() != null){
            List<IntroduceRequestDto> introduceRequests = resumeDto.getIntroduces();
            for(IntroduceRequestDto intro : introduceRequests){
                Introduce introduce = converterUtil.introduceToEntity(res, intro);
                introduceRepository.save(introduce);
            }
        }

        return 1;
    }

    @Override
    public JobSeekerUserResponseDto getUserInfo(String username) {
        JobSeekerUserResponseDto jobSeeker = new JobSeekerUserResponseDto();

        JobSeeker seeker = jobSeekerRepository.findByUsername(username);

        Optional<User> user = userRepository.findByUsername(username);

        String addr1 = seeker.getAddress();
        String addr2 = seeker.getAddressDetail();

        String address = addr1 + " " + addr2;

        jobSeeker.setUsername(username);
        jobSeeker.setName(user.get().getName());
        jobSeeker.setEmail(seeker.getEmail());
        jobSeeker.setPhone(seeker.getPhone());
        jobSeeker.setAddress(address.trim());

        return jobSeeker;
    }

}
