package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.SearchRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    private final SearchRepository searchRepository;

    public List<JobPostSearchDto> searchJobPosts(String keyword) {
        List<Object[]> results = searchRepository.findJobPostByKeywordNative(keyword);
        return results.stream()
                .map(result -> JobPostSearchDto.builder()
                        .title((String) result[0])
                        .companyName((String) result[1])
                        .skills((String) result[2])
                        .companyAddress((String) result[3])
                        .jobHistory((Integer) result[4])
                        .profileImage(result[5] != null ? (byte[]) result[5] : null)
                        .build())
                .collect(Collectors.toList());
    }

    public List<CompanySearchDto> searchCompanies(String keyword) {
        List<Object[]> results = searchRepository.findCompanyByKeywordNative(keyword);
        return results.stream()
                .map(result -> CompanySearchDto.builder()
                        .companyId((String) result[0])
                        .name((String) result[1])
                        .address((String) result[2])
                        .birth(((java.sql.Date) result[3]).toLocalDate())
                        .averageScore(result[4] != null ? ((BigDecimal) result[4]).doubleValue() : null)
                        .build())
                .collect(Collectors.toList());
    }
}