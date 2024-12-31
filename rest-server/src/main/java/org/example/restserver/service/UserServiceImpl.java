package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.*;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobSeeker;
import org.example.restserver.entity.User;
import org.example.restserver.repository.CompanyRepository;
import org.example.restserver.repository.JobSeekerRepository;
import org.example.restserver.repository.UserRepository;
import org.example.restserver.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : org.example.restserver.service
 * fileName       : UserServiceImpl
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final JobSeekerRepository jobSeekerRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public int register(UserRequestDto userRequestDto) {
        String role = userRequestDto.getRole();
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userRequestDto.getPassword()))
                .name(userRequestDto.getName())
                .role(userRequestDto.getRole())
                .build();

        userRepository.save(user);

        if (role.equals("ROLE_JOB_SEEKER")) {
            JobSeeker jobSeeker = modelMapper.map(userRequestDto.getJobSeeker(), JobSeeker.class);
            jobSeekerRepository.save(jobSeeker);
        } else if (role.equals("ROLE_COMPANY")) {
            Company company = modelMapper.map(userRequestDto.getCompany(), Company.class);
            companyRepository.save(company);
        }

        return 1;
    }

    @Override
    public Map<String, Object> login(UserRequestDto userRequestDto) {
        Map<String, Object> map = new HashMap<>();

        User user;

        List<String> roleList = new ArrayList<>();
        roleList.add(userRequestDto.getRole());

        if (userRequestDto.getRole().equals("ROLE_JOB_SEEKER")) {
            roleList.add("ROLE_ADMIN");
        }

        user = userRepository.findByUsernameAndRoleInAndDeleteYn(
                userRequestDto.getUsername(),
                roleList,
                "N"
        ).orElse(null);

        if (user == null || !bCryptPasswordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호를 확인해주세요.");
        }
        String token = jwtUtil.createToken(user.getUsername(), user.getName(), user.getRole());
        map.put("token", token);

        map.put("user", new SessionUserDto(user.getUsername(), user.getName(), user.getRole()));

        return map;
    }

    @Override
    public boolean checkDuplicationUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserResponseDto getUser(String username) {
        UserResponseDto userDto = userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .orElse(null);

        String userRole = userDto.getRole();
        if (userRole.equals("ROLE_JOB_SEEKER")) {
            JobSeekerResponseDto jobSeekerDto =
                    jobSeekerRepository.findById(username)
                            .map(jobSeeker -> modelMapper.map(jobSeeker, JobSeekerResponseDto.class))
                            .orElse(null);
            jobSeekerDto.setUsername(userDto.getUsername());
            jobSeekerDto.setName(userDto.getName());
            jobSeekerDto.setRole(userRole);
            return jobSeekerDto;
        } else if (userRole.equals("ROLE_COMPANY")) {
            CompanyResponseDto CompanyDto =
                    companyRepository.findById(username)
                            .map(company -> modelMapper.map(company, CompanyResponseDto.class))
                            .orElse(null);
            CompanyDto.setUsername(userDto.getUsername());
            CompanyDto.setName(userDto.getName());
            CompanyDto.setRole(userRole);
            return CompanyDto;
        }
        return userDto;
    }

    @Override
    @Transactional
    public int update(String username, UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("없어용~"));

        if(!userRequestDto.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        }
        user.setName(userRequestDto.getName());
        user.setRole(userRequestDto.getRole());

        userRepository.save(user);

        String role = user.getRole();
        if ("ROLE_JOB_SEEKER".equals(role)) {
            JobSeeker jobSeeker = jobSeekerRepository.findById(user.getUsername())
                    .orElse(new JobSeeker());

            modelMapper.map(userRequestDto.getJobSeeker(), jobSeeker);
            jobSeekerRepository.save(jobSeeker);
        } else if ("ROLE_COMPANY".equals(role)) {
            Company company = companyRepository.findById(user.getUsername())
                    .orElse(new Company());

            modelMapper.map(userRequestDto.getCompany(), company);
            companyRepository.save(company);
        }

        return 1;
    }
}

