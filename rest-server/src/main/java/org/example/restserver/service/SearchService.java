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
import java.util.Base64;
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

    public List<JobPostSearchDto> searchJobPostsByKeyword(String keyword) {
        return searchRepository.findJobPostByKeywordNative(keyword).stream()
                .map(result -> JobPostSearchDto.builder()
                        .title((String) result[0])          // 공고 제목
                        .companyName((String) result[1])    // 회사명
                        .skills((String) result[2])         // 기술 스택
                        .companyAddress((String) result[3]) // 회사 주소
                        .jobHistory((Integer) result[4])    // 경력
                        .educationCode((String) result[5])  // 학력 코드
                        .jobRankCode((String) result[6])    // 직급 코드
                        .workTypeCode((String) result[7])   // 근무 형태 코드
                        .profileImage(
                                result[8] != null
                                        ? Base64.getEncoder().encodeToString((byte[]) result[8])
                                        : null)                         // 프로필 이미지 (Base64 인코딩)
                        .endDate(result[9] != null
                                ? ((java.sql.Timestamp) result[9]).toLocalDateTime().toLocalDate()
                                : null)                         // 마감일자
                        .build()
                )
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