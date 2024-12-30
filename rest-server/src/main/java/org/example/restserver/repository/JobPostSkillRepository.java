package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.entity.JobPost;
import org.example.restserver.entity.JobPostSkill;
import org.example.restserver.entity.JobPostSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Query("SELECT jp FROM JobPost jp JOIN JobPostSkill jps ON jp.jobPostNo = jps.id.jobPostNo " +
            "JOIN Gubun g ON jps.id.skillCode = g.id.code WHERE g.id.gubunCode = 'SKILL' AND g.id.code = :skillCode")
    List<JobPost> findJobPostsBySkillCode(@Param("skillCode") String skillCode);

}