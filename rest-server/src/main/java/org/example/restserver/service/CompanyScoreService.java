package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.entity.CompanyScore;
import org.example.restserver.repository.CompanyScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : CompanyScoreService
 * author         : 이동하
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class CompanyScoreService {

    private final CompanyScoreRepository companyScoreRepository;

    public List<CompanyScore> getCompanyScores(String companyId) {
        List<CompanyScore> byIdCompanyId = companyScoreRepository.findByIdCompanyId(companyId);
        System.out.println(byIdCompanyId);
        return byIdCompanyId;
    }

    public Double getAverageScore(String companyId) {
        return companyScoreRepository.findAverageScoreByCompanyId(companyId);
    }
}
