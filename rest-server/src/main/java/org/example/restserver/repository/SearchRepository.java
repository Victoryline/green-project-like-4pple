package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : SearchRepository
 * author         : 이동하
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        이동하       최초 생성
 */

public interface SearchRepository extends JpaRepository<User, String> { // Object와 Long은 사용하지 않는 기본 값

    @Query("SELECT new org.example.restserver.dto.JobPostSearchDto(" +
            "j.username, j.title, j.workCode, j.jobHistory, j.educationCode, " +
            "j.jobRankCode, j.workTypeCode, j.endDate) " +
            "FROM JobPost j WHERE j.title LIKE %:keyword%")
    List<JobPostSearchDto> findJobPostByKeyword(@Param("keyword") String keyword);

    @Query("SELECT new org.example.restserver.dto.CompanySearchDto(" +
            "u.username, u.name, c.address, c.birth, " +
            "(SELECT AVG(cs.score) FROM CompanyScore cs WHERE cs.id.companyId = u.username)) " +
            "FROM User u LEFT JOIN Company c ON u.username = c.username " +
            "WHERE u.role = 'ROLE_COMPANY' AND u.deleteYn = 'N' AND u.name LIKE %:keyword%")
    List<CompanySearchDto> findCompanyByKeyword(@Param("keyword") String keyword);

    @Query("SELECT new org.example.restserver.dto.JobPostSearchDto(" +
            "j.username, j.title, j.workCode, j.jobHistory, j.educationCode, " +
            "j.jobRankCode, j.workTypeCode, j.endDate) " +
            "FROM JobPost j")
    List<JobPostSearchDto> findAllJobPosts();

    @Query("SELECT new org.example.restserver.dto.CompanySearchDto(" +
            "u.username, u.name, c.address, c.birth, " +
            "(SELECT AVG(cs.score) FROM CompanyScore cs WHERE cs.id.companyId = u.username)) " +
            "FROM User u LEFT JOIN Company c ON u.username = c.username " +
            "WHERE u.role = 'ROLE_COMPANY' AND u.deleteYn = 'N'")
    List<CompanySearchDto> findAllCompanies();
}
