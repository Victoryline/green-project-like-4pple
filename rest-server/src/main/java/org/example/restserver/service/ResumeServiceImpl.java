package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.*;
import org.example.restserver.entity.*;
import org.example.restserver.repository.*;
import org.example.restserver.utils.ConverterUtil;
import org.example.restserver.utils.GubunUttil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final ModelMapper modelMapper;

    private final GubunUttil gubunUttil;

    @Override
    public int postResume(ResumeRequestDto resumeDto) {

        log.info(String.valueOf(resumeDto));

        Resume resume = new Resume();

        if (resumeDto.getResumeNo() != 0) {
            resume.setId(resumeDto.getResumeNo());
        }

        resume.setUsername(resumeDto.getUsername());
        resume.setTitle(resumeDto.getCaption());
        resume.setImage(resumeDto.getImage());
        resume.setWishArea(resumeDto.getWishArea());
        resume.setWishSalary(resumeDto.getWishSalary());
        resume.setWishTime(resumeDto.getWishTime());
        resume.setWorkCode(resumeDto.getWorkCode());

        Resume res = resumeRepository.save(resume);

        if (resumeDto.getEducations() != null) {
            List<EducationRequestDto> educations = resumeDto.getEducations();
            for (EducationRequestDto edu : educations) {
                Education education = converterUtil.dtoToEntity(res, edu);
                educationRepository.save(education);
            }
        }


        if (resumeDto.getMilitary() != null) {
            List<MilitaryRequestDto> military = resumeDto.getMilitary();
            log.info(military.toString());
            for (MilitaryRequestDto mil : military) {
                Military militaryEntity = converterUtil.militaryToEntity(res, mil);
                militaryRepository.save(militaryEntity);
            }
        }

        if (resumeDto.getActivities() != null) {
            List<ActivityRequestDto> activities = resumeDto.getActivities();
            for (ActivityRequestDto act : activities) {
                Activity activity = converterUtil.activityToEntity(res, act);
                activityRepository.save(activity);
            }
        }

        if (resumeDto.getLicenses() != null) {
            List<LicenseRequestDto> licenses = resumeDto.getLicenses();
            for (LicenseRequestDto lic : licenses) {
                License license = converterUtil.licenseToEntity(res, lic);
                licenseRepository.save(license);
            }
        }

        if (resumeDto.getSkills() != null) {
            List<SkillCodeRequestDto> skills = resumeDto.getSkills();
            for (SkillCodeRequestDto skill : skills) {
                ResumeSkill resumeSkill = converterUtil.skillToEntity(res, skill);
                resumeSkillRepository.save(resumeSkill);
            }
        }

        if (resumeDto.getPortfolios() != null) {
            List<PortfolioRequestDto> portfolios = resumeDto.getPortfolios();
            for (PortfolioRequestDto port : portfolios) {
                Potfolio portfolio = converterUtil.potfolioToEntity(res, port);
                portfolioRepository.save(portfolio);
            }
        }

        if (resumeDto.getIntroduces() != null) {
            List<IntroduceRequestDto> introduceRequests = resumeDto.getIntroduces();
            for (IntroduceRequestDto intro : introduceRequests) {
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

    @Override
    public ResumeResponseDto getResumeInfo(int resumeNo) {

        ResumeResponseDto resumeResponse = new ResumeResponseDto();

        // 각 엔티티로 바다엇 리스폰스로 돌인다.
        List<Activity> activities = activityRepository.findById_ResumeNo(resumeNo);
        log.info(activities.toString());

        if (activities != null && !activities.isEmpty()) {
            List<ActivityRequestDto> activityList = activities.stream()
                    .map(activity -> {
                        ActivityRequestDto dto = modelMapper.map(activity, ActivityRequestDto.class);
//                        String name = gubunUttil.getCode(activity.getId().getActivityType());
                        dto.setActivityType(activity.getId().getActivityType()); // 추가 필드 설정
                        return dto;
                    })
                    .collect(Collectors.toList());

            log.info(activityList.toString());
            resumeResponse.setActivities(activityList);
        }

        Education educations = educationRepository.findById(resumeNo);
        if (educations != null) {
            log.info("educations : " + educations);
            EducationRequestDto request = modelMapper.map(educations, EducationRequestDto.class);
            log.info("request : " + educations.getEducationCode());
            if (educations.getEducationCode() != null) {
                String name = gubunUttil.getCode(educations.getEducationCode());
                request.setEducationCode(name);
            } else {
                // 값이 없을 때 처리 (예: 기본값을 설정하거나 예외를 던짐)
                request.setEducationCode("Unknown");
            }
            resumeResponse.setEducations(request);
            log.info(request + "request");
        }


        List<Introduce> introduces = introduceRepository.findAllById_resumeNo(resumeNo);
        if (introduces != null) {
            log.info("introduces : " + introduces);
            List<IntroduceRequestDto> request = introduces.stream()
                    .map(data ->{
                        IntroduceRequestDto dto = modelMapper.map(data, IntroduceRequestDto.class);
                        dto.setTitle(String.valueOf(data.getId().getTitle())); // 추가 필드 설정
                        return dto;
                    })
                    .collect(Collectors.toList());
            log.info(request.toString());
            resumeResponse.setIntroduces(request);
        }


        List<License> licenses = licenseRepository.findById_ResumeNo(resumeNo);
        log.info(licenses.toString());
        if (licenses != null) {
            log.info("licenses : " + licenses);
            List<LicenseRequestDto> request = licenses.stream()
                    .map(data ->{
                        LicenseRequestDto dto = modelMapper.map(data, LicenseRequestDto.class);
                        dto.setLicenseName(String.valueOf(data.getId().getLicenseName())); // 추가 필드 설정
                        return dto;
                    })
                    .collect(Collectors.toList());
            log.info(request.toString());
            resumeResponse.setLicenses(request);
        }

        Military military = militaryRepository.findById(resumeNo);
        if (military != null) {
            log.info("military : " + military);
            MilitaryRequestDto request = modelMapper.map(military, MilitaryRequestDto.class);
            log.info("2" +military.getMilitaryCode());
            if (military.getMilitaryCode() != null) {
                String name = gubunUttil.getCode(military.getMilitaryCode());
                request.setMilitaryCode(name);
            } else {
                // 값이 없을 때 처리 (예: 기본값을 설정하거나 예외를 던짐)
                request.setMilitaryCode("Unknown");
            }
            resumeResponse.setMilitary(request);
        }

        List<Potfolio> portfolios = portfolioRepository.findById_ResumeNo(resumeNo);
        if (portfolios != null) {
            log.info("portfolios : " + portfolios);
            List<PortfolioRequestDto> portfolio = portfolios.stream()
                    .map(port -> {
                        PortfolioRequestDto dto = modelMapper.map(port, PortfolioRequestDto.class);
                        dto.setPortfolioFilename(String.valueOf(port.getId().getPortfolioFilename())); // 추가 필드 설정
                        return dto;
                    })
                    .collect(Collectors.toList());

            log.info(portfolio.toString());
            resumeResponse.setPortfolios(portfolio);
        }

        List<ResumeSkill> skills = resumeSkillRepository.findById_ResumeNo(resumeNo);
        if (skills != null) {
            log.info("skill : " + skills);
            List<SkillCodeRequestDto> request = skills.stream()
                    .map(data -> modelMapper.map(data.getId(), SkillCodeRequestDto.class))
                    .collect(Collectors.toList());
            log.info(request.toString());
            resumeResponse.setSkills(request);
        }

        Resume resume = resumeRepository.findById(resumeNo).orElse(null);
        if (resume != null) {
            log.info("Resume : " + resume);
            resumeResponse.setUsername(resume.getUsername());
            resumeResponse.setTitle(resume.getTitle());
            resumeResponse.setImage(resume.getImage());
            resumeResponse.setWishArea(resume.getWishArea());
            resumeResponse.setWishSalary(resume.getWishSalary());
            resumeResponse.setWishTime(resume.getWishTime());
//            String workCode = gubunUttil.getCode(resume.getWorkCode());
            log.info("1" + resume.getWorkCode());
            if (resume.getWorkCode() != null) {

                String workCode = gubunUttil.getCode(resume.getWorkCode());

                resumeResponse.setWorkCode(workCode);

            } else {
                // 값이 없을 때 처리 (예: 기본값을 설정하거나 예외를 던짐)
                resumeResponse.setWorkCode("Unknown");
            }
        }

        JobSeeker jobSeeker = jobSeekerRepository.findByUsername(resume.getUsername());
        if (jobSeeker != null) {
            JobSeekerUserResponseDto request = modelMapper.map(jobSeeker, JobSeekerUserResponseDto.class);
            resumeResponse.setUser(request);
        }

        return resumeResponse;
    }

    @Override
    public List<ResumeResponseDto> getAllResumes(String username) {

        List<Resume> resumeList = resumeRepository.findAllByUsername(username);
        List<ResumeResponseDto> request = new ArrayList<>();
        if (resumeList != null) {
            for (Resume resume : resumeList) {
                ResumeResponseDto dto = modelMapper.map(resume, ResumeResponseDto.class);
                dto.setResumeNo(String.valueOf(resume.getId()));
                if (resume.getWorkCode() != null) {

                    String workCode = gubunUttil.getCode(resume.getWorkCode());

                    dto.setWorkCode(workCode);

                } else {
                    // 값이 없을 때 처리 (예: 기본값을 설정하거나 예외를 던짐)
                    dto.setWorkCode("Unknown");
                }
                request.add(dto);
            }
        }
        return request;
    }

    @Override
    public void deleteResume(int resumeNo) {

        resumeRepository.deleteById(resumeNo);
        activityRepository.deleteById_ResumeNo(resumeNo);
        educationRepository.deleteById(resumeNo);
        introduceRepository.deleteAllById_ResumeNo(resumeNo);
        licenseRepository.deleteAllById_ResumeNo(resumeNo);
        militaryRepository.deleteById(resumeNo);
        portfolioRepository.deleteAllById_ResumeNo(resumeNo);
        resumeSkillRepository.deleteAllById_ResumeNo(resumeNo);

    }

}
