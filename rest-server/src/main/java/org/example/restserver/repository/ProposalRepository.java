package org.example.restserver.repository;

import org.example.restserver.entity.Like;
import org.example.restserver.entity.Proposal;
import org.example.restserver.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : ProposalRepository
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */
@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

    @Query("SELECT l.id.jobSeekerId FROM Like l WHERE l.id.companyId = :companyId AND l.id.likeType = 'C'")
    List<String> findBookmarkedJobSeeker(@Param("companyId") String companyId);

    @Query("SELECT r FROM Resume r WHERE r.username IN :userIds")
    List<Resume> findResume(@Param("userIds") List<String> userIds);

    @Query("SELECT r FROM Resume r WHERE r.id = :resumeId")
    Resume findResumeDetail(@Param("resumeId") Integer resumeId);
}