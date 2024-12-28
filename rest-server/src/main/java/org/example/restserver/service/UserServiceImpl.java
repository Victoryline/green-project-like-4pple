package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.UserRequestDto;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobSeeker;
import org.example.restserver.entity.User;
import org.example.restserver.repository.CompanyRepository;
import org.example.restserver.repository.JobSeekerRepository;
import org.example.restserver.repository.UserRepository;
import org.example.restserver.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            JobSeeker jobSeeker = userRequestDto.getJobSeeker();
            jobSeekerRepository.save(jobSeeker);
        } else if (role.equals("ROLE_COMPANY")) {
            Company company = userRequestDto.getCompany();
            companyRepository.save(company);
        }

        return 1;
    }

    @Override
    public String login(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElse(null);

        if (user == null || !bCryptPasswordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호를 확인해주세요.");
        }
        // JWT 생성
        return jwtUtil.createToken(user.getUsername(), user.getName(), user.getRole());
    }

    @Override
    public boolean checkDuplicationUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
