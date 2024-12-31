package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : HeaderSerch
 * author         : 이동하
 * date           : 2024-12-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-29        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<CompanySearchDto> searchCompanies(String keyword) { // 회사 명으로 찾기
        List<CompanySearchDto> companyByKeyword = userRepository.findCompanyByKeyword(keyword);
        System.out.println(companyByKeyword);
        return companyByKeyword;
    }

    public List<JobPostSearchDto> searchJobPosts(String keyword) { // 채용공고로 찾기
        return jobPostRepository.findJobPostByKeyword(keyword);
    }

}
