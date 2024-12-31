package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanyScoreDto;
import org.example.restserver.entity.CompanyScore;
import org.example.restserver.entity.CompanyScoreId;
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

    public void addScore(CompanyScoreDto companyScoreDto) {
        CompanyScoreId scoreId = new CompanyScoreId();
        scoreId.setCompanyId(companyScoreDto.getCompanyId());
        scoreId.setJobSeekerId(companyScoreDto.getJobSeekerId());

        CompanyScore companyScore = new CompanyScore();
        companyScore.setId(scoreId);
        companyScore.setScore(companyScoreDto.getScore());

        companyScoreRepository.save(companyScore);
    }
}
