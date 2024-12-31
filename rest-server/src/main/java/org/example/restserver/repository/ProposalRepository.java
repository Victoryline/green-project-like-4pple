package org.example.restserver.repository;

import org.example.restserver.entity.JobSeeker;
import org.example.restserver.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    List<Proposal> findByJobSeekerId(JobSeeker jobSeekerId);
}
