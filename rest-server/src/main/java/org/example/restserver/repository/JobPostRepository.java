package org.example.restserver.repository;

import org.example.restserver.dto.JobPostResponseDto;
import org.example.restserver.dto.MainJobPostDto;
import org.example.restserver.entity.JobPost;
import org.example.restserver.utils.ImageUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    @Query(value = "SELECT j.job_post_no AS jobPostNo, j.username AS username, u.name AS name, j.title AS title, " +
            "j.work_code AS workCode, j.job_history AS jobHistory, j.job_salary AS jobSalary, " +
            "j.start_date AS startDate, j.end_date AS endDate, j.work_condition AS workCondition, j.end_yn AS endYn, " +
            "(SELECT GROUP_CONCAT(gb.name) " +
            "FROM tbl_job_post_skill jc " +
            "JOIN tbl_gubun gb ON jc.skill_code = gb.code AND gb.gubun_code = 'SKILL' " +
            "WHERE jc.job_post_no = j.job_post_no) AS jobPostSkills, " +
            "c.address, c.profile_image " +
            "FROM tbl_job_post j " +
            "JOIN tbl_user u ON j.username = u.username AND u.delete_yn = 'N' AND u.role = 'ROLE_COMPANY' " +
            "JOIN tbl_company c ON u.username = c.username " +
            "WHERE j.end_yn = 'N' AND j.start_date <= NOW() AND (j.end_date IS NULL OR j.end_date > NOW())",
            nativeQuery = true)
    List<Object[]> findRawJobPosts();

    default List<JobPostResponseDto> findActiveJobPostsWithCompanyInfo() {
        return findRawJobPosts().stream()
                .map(result -> new JobPostResponseDto(
                        (Integer) result[0],  // jobPostNo
                        (String) result[1],  // username
                        (String) result[2],  // companyName
                        (String) result[3],  // title
                        (String) result[4],  // workCode
                        (Integer) result[5], // jobHistory
                        (Integer) result[6], // jobSalary
                        ((java.sql.Timestamp) result[7]).toLocalDateTime().toLocalDate(), // startDate
                        result[8] != null ? ((java.sql.Timestamp) result[8]).toLocalDateTime().toLocalDate() : null, // endDate
                        (String) result[9],  // workCondition
                        (Character) result[10], // endYn
                        (String) result[11],   // jobPostSkills
                        (String) result[12],
                        (byte[]) result[13]
                ))
                .collect(Collectors.toList());
    }


    @Query(value =
            """
                    SELECT A.*,
                           IFNULL((SELECT COUNT(username)
                                   FROM tbl_scrap
                                   WHERE tbl_scrap.job_post_no = A.jobPostNo), 0) AS scrap_cnt,
                           CASE
                               WHEN A.endDate IS NULL THEN '상시'
                               ELSE CONCAT('D-', DATEDIFF(A.endDate, NOW()))
                           END AS `day`
                    FROM (
                        SELECT j.job_post_no AS jobPostNo,
                               j.username AS username,
                               u.name AS companyName,
                               j.title AS title,
                               j.work_code AS workCode,
                               j.job_history AS jobHistory,
                               j.job_salary AS jobSalary,
                               j.start_date AS startDate,
                               j.end_date AS endDate,
                               j.work_condition AS workCondition,
                               j.end_yn AS endYn,
                               (SELECT GROUP_CONCAT(gb.name)
                                FROM tbl_job_post_skill jc
                                JOIN tbl_gubun gb ON jc.skill_code = gb.code AND gb.gubun_code = 'SKILL'
                                WHERE jc.job_post_no = j.job_post_no) AS jobPostSkills,
                               c.address,
                               c.profile_image
                        FROM tbl_job_post j
                        JOIN tbl_user u ON j.username = u.username
                                         AND u.delete_yn = 'N'
                                         AND u.role = 'ROLE_COMPANY'
                        JOIN tbl_company c ON u.username = c.username
                        WHERE j.end_yn = 'N'
                          AND j.start_date <= NOW()
                          AND (j.end_date IS NULL OR j.end_date > NOW())
                        LIMIT 8
                    ) A
                    ORDER BY scrap_cnt
                    """,
            nativeQuery = true)
    List<Object[]> findPopularJobPosts();

    default List<MainJobPostDto> findPopularJobPostsWithCompanyInfo() {
        return findPopularJobPosts().stream()
                .map(result -> new MainJobPostDto(
                        (Integer) result[0],  // jobPostNo
                        (String) result[1],  // username
                        (String) result[2],  // companyName
                        (String) result[3],  // title
                        (String) result[4],  // workCode
                        (Integer) result[5], // jobHistory
                        (Integer) result[6], // jobSalary
                        ((java.sql.Timestamp) result[7]).toLocalDateTime().toLocalDate(), // startDate
                        result[8] != null ? ((java.sql.Timestamp) result[8]).toLocalDateTime().toLocalDate() : null, // endDate
                        (String) result[9],  // workCondition
                        (Character) result[10], // endYn
                        (String) result[11],   // jobPostSkills
                        (String) result[12],  // address
                        ImageUtil.encodeToBase64((byte[]) result[13]),  // profileImage
                        (String) result[15]
                ))
                .collect(Collectors.toList());
    }

    @Query(value =
            """
                        SELECT j.job_post_no AS jobPostNo,
                               j.username AS username,
                               u.name AS companyName,
                               j.title AS title,
                               j.work_code AS workCode,
                               j.job_history AS jobHistory,
                               j.job_salary AS jobSalary,
                               j.start_date AS startDate,
                               j.end_date AS endDate,
                               j.work_condition AS workCondition,
                               j.end_yn AS endYn,
                               (SELECT GROUP_CONCAT(gb.name)
                                FROM tbl_job_post_skill jc
                                JOIN tbl_gubun gb ON jc.skill_code = gb.code AND gb.gubun_code = 'SKILL'
                                WHERE jc.job_post_no = j.job_post_no) AS jobPostSkills,
                               c.address,
                               c.profile_image,
                               CASE
                               WHEN j.end_date IS NULL THEN '상시'
                               ELSE CONCAT('D-', DATEDIFF(j.end_date, NOW()))
                               END AS `day`
                        FROM tbl_job_post j
                        JOIN tbl_user u ON j.username = u.username
                                         AND u.delete_yn = 'N'
                                         AND u.role = 'ROLE_COMPANY'
                        JOIN tbl_company c ON u.username = c.username
                        WHERE j.end_yn = 'N'
                          AND j.username = :username
                          AND j.start_date <= NOW()
                          AND (j.end_date IS NULL OR j.end_date > NOW())
                          ORDER BY j.end_date
                    """,
            nativeQuery = true)
    List<Object[]> findJobPosts(String username);

    default List<MainJobPostDto> findJobPostsWithCompanyInfo(String username) {
        return findJobPosts(username).stream()
                .map(result -> new MainJobPostDto(
                        (Integer) result[0],  // jobPostNo
                        (String) result[1],  // username
                        (String) result[2],  // companyName
                        (String) result[3],  // title
                        (String) result[4],  // workCode
                        (Integer) result[5], // jobHistory
                        (Integer) result[6], // jobSalary
                        ((java.sql.Timestamp) result[7]).toLocalDateTime().toLocalDate(), // startDate
                        result[8] != null ? ((java.sql.Timestamp) result[8]).toLocalDateTime().toLocalDate() : null, // endDate
                        (String) result[9],  // workCondition
                        (Character) result[10], // endYn
                        (String) result[11],   // jobPostSkills
                        (String) result[12],  // address
                        ImageUtil.encodeToBase64((byte[]) result[13]),  // profileImage
                        (String) result[14]
                ))
                .collect(Collectors.toList());
    }

}