package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobSillRepository
 * author         : 이동하
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        이동하       최초 생성
 */
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    @Query("select jp from JobPost jp where jp.workCode = :workCode")
    List<JobPost> findByWorkCode(@Param("workCode") String workCode);
}
