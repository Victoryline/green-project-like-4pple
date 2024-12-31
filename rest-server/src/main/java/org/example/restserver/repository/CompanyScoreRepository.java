package org.example.restserver.repository;

import org.example.restserver.entity.CompanyScore;
import org.example.restserver.entity.CompanyScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : CompanyScoreRepository
 * author         : 이동하
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        이동하       최초 생성
 */
@Repository
public interface CompanyScoreRepository extends JpaRepository<CompanyScore, CompanyScoreId> {
    List<CompanyScore> findByIdCompanyId(String companyId);

    @Query("SELECT AVG(cs.score) FROM CompanyScore cs WHERE cs.id.companyId = :companyId")
    Double findAverageScoreByCompanyId(String companyId);

}
