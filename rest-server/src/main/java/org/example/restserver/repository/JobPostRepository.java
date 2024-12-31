package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;

import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
        @Query("SELECT " +
                "j.title, " +
                "jc.id.skillCode, " + // 복합키로 접근: JobPostSkillId의 skillCode
                "c.username, " +
                "c.address, " +
                "j.username " +
                "FROM JobPost j " +
                "JOIN Company c ON j.username = c.username " +
                "JOIN JobPostSkill jc ON jc.jobPost.jobPostNo = j.jobPostNo")  // jobPostNo는 JobPost 엔티티에서 가져옴
        List<Object[]> findAllJobPostsWithCompanyInfo();

}