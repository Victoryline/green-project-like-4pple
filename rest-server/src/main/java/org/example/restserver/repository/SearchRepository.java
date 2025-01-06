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

public interface SearchRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT j.title, u.name AS companyName, " +
            "GROUP_CONCAT(js.skill_code) AS skills, " +
            "c.address AS companyAddress, j.job_history AS jobHistory, " +
            "c.profile_image " +  // 프로필 이미지 추가
            "FROM tbl_job_post j " +
            "JOIN tbl_company c ON j.username = c.username " +
            "JOIN tbl_user u ON c.username = u.username " +
            "LEFT JOIN tbl_job_post_skill js ON js.job_post_no = j.job_post_no " +
            "WHERE j.title LIKE %:keyword% " +
            "GROUP BY j.job_post_no", nativeQuery = true)
    List<Object[]> findJobPostByKeywordNative(@Param("keyword") String keyword);

    @Query(value = "SELECT u.username, u.name, c.address, c.birth, " +
            "(SELECT AVG(cs.score) FROM tbl_company_score cs WHERE cs.company_id = u.username) AS averageScore " +
            "FROM tbl_user u " +
            "LEFT JOIN tbl_company c ON u.username = c.username " +
            "WHERE u.role = 'ROLE_COMPANY' AND u.delete_yn = 'N' AND u.name LIKE %:keyword%",
            nativeQuery = true)
    List<Object[]> findCompanyByKeywordNative(@Param("keyword") String keyword);

    @Query(value = "SELECT j.title, u.name AS companyName, " +
            "GROUP_CONCAT(js.skill_code) AS skills, " +
            "c.address AS companyAddress, j.job_history AS jobHistory " +
            "FROM tbl_job_post j " +
            "JOIN tbl_company c ON j.username = c.username " +
            "JOIN tbl_user u ON c.username = u.username " +
            "LEFT JOIN tbl_job_post_skill js ON js.job_post_no = j.job_post_no " +
            "GROUP BY j.job_post_no", nativeQuery = true)
    List<Object[]> findAllJobPostsNative();

    @Query(value = "SELECT u.username, u.name, c.address, c.birth, " +
            "(SELECT AVG(cs.score) FROM tbl_company_score cs WHERE cs.company_id = u.username) AS averageScore " +
            "FROM tbl_user u " +
            "LEFT JOIN tbl_company c ON u.username = c.username " +
            "WHERE u.role = 'ROLE_COMPANY' AND u.delete_yn = 'N'",
            nativeQuery = true)
    List<Object[]> findAllCompaniesNative();
}
