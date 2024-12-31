package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;

import org.example.restserver.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobPost
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */
@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
        @Query("SELECT j.title, j.jobPostSkills, c.username, c.address FROM JobPost j JOIN Company c")
        List<Object[]> findAllJobPostsWithCompanyInfo();
}