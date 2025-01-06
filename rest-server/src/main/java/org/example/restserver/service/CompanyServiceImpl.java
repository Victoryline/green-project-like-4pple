package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.CompanyResponseDto;
import org.example.restserver.entity.Company;
import org.example.restserver.entity.User;
import org.example.restserver.repository.CompanyRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * packageName    : org.example.restserver.service
 * fileName       : CompanyServiceImpl
 * author         : 김재홍
 * date           : 25. 1. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 5.        김재홍       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Override
    public CompanyResponseDto getCompany(String username) {

        CompanyResponseDto dto = new CompanyResponseDto();

        Company company = companyRepository.findByUsername(username);

        Optional<User> user = userRepository.findByUsername(username);

        dto.setBusinessNumber(company.getBusinessNumber());
        dto.setAddress(company.getAddress());
        dto.setInfo(company.getInfo());
        dto.setImage(company.getProfileImage());
        dto.setAddress(company.getAddress());
        dto.setAddressDetail(company.getAddressDetail());
        dto.setZonecode(company.getZoneCode());
        dto.setContact(company.getContact());
        dto.setWebsite(company.getWebsite());
        dto.setEmail(company.getEmail());
        dto.setBirth(company.getBirth());
        dto.setEmployee(company.getEmployee());
        dto.setName(user.get().getName());

        return dto;

    }
}
