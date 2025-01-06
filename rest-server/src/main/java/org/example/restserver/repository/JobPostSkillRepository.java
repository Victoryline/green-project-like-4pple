package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.entity.JobPost;
import org.example.restserver.entity.JobPostSkill;
import org.example.restserver.entity.JobPostSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobPost
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */
@Repository
public interface JobPostSkillRepository extends JpaRepository<JobPostSkill, JobPostSkillId> {

    @Query("""
                SELECT jp 
                FROM JobPost jp 
                JOIN JobPostSkill jps ON jp.jobPostNo = jps.id.jobPostNo 
                JOIN Gubun g ON jps.id.skillCode = g.id.code 
                WHERE g.id.gubunCode = 'SKILL' 
                  AND g.id.code = :skillCode
                  AND jp.endYn = 'N'
                  AND jp.startDate <= CURRENT_TIMESTAMP
                  AND (jp.endDate IS NULL OR jp.endDate > CURRENT_TIMESTAMP)
            """)
    List<JobPost> findJobPostsBySkillCode(@Param("skillCode") String skillCode);


    @Query("SELECT DISTINCT jp FROM JobPost jp " +
            "JOIN jp.jobPostSkills jps " +
            "WHERE jps.id.skillCode IN :skillCodes")
    List<JobPost> findJobPostsBySkillCodes(@Param("skillCodes") List<String> skillCodes);

    @Query("""
                SELECT jps
                FROM JobPostSkill jps
                JOIN FETCH jps.jobPost jp
                JOIN FETCH jp.company
                WHERE jp.endYn = 'N'
                  AND jp.startDate <= CURRENT_TIMESTAMP
                  AND (jp.endDate IS NULL OR jp.endDate > CURRENT_TIMESTAMP)
            """)
    List<JobPostSkill> findAllJobPosts();

    @Query("SELECT jps FROM JobPostSkill jps " +
            "JOIN FETCH jps.jobPost jp " +
            "WHERE jps.id.skillCode = :skillCode")
    List<JobPostSkill> findJobPostSkillsBySkillCode(@Param("skillCode") String skillCode);

    //    @Query("SELECT jps FROM JobPostSkill jps " +
//            "JOIN FETCH jps.jobPost jp " +
//            "WHERE jps.id.skillCode IN :skillCodes")
    @Query(value = """
                SELECT jps.*
                FROM tbl_job_post_skill jps
                JOIN tbl_job_post jp ON jps.job_post_no = jp.job_post_no
                WHERE jps.skill_code IN (:skillCodes)
                  AND jp.end_yn = 'N'
                  AND jp.start_date <= NOW()
                  AND (jp.end_date IS NULL OR jp.end_date > NOW())
            """, nativeQuery = true)
    List<JobPostSkill> findJobPostSkillsBySkillCodes(@Param("skillCodes") List<String> skillCodes);

    Optional<JobPostSkill> findById(JobPostSkillId id);

}