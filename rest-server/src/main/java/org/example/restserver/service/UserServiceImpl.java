package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanyRequestDto;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobSeeker;
import org.example.restserver.entity.User;
import org.example.restserver.repository.CompanyRepository;
import org.example.restserver.repository.JobSeekerRepository;
import org.example.restserver.repository.UserRepository;
import org.example.restserver.utils.ImageUtil;
import org.example.restserver.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
        }
        else if (role.equals("ROLE_COMPANY")) {
            Company company = modelMapper.map(userRequestDto.getCompany(), Company.class);
            companyRepository.save(company);
        }

        return 1;
    }

    @Override
    public String login(UserRequestDto userRequestDto) {
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

        return jwtUtil.createToken(user.getUsername(), user.getName(), user.getRole());
    }

    @Override
    public boolean checkDuplicationUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
