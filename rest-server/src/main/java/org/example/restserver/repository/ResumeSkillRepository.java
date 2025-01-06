package org.example.restserver.repository;

import org.example.restserver.dto.SkillCodeRequestDto;
import org.example.restserver.entity.ResumeSkill;
import org.example.restserver.entity.ResumeSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : ResumeSkillRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface ResumeSkillRepository extends JpaRepository<ResumeSkill, ResumeSkillId> {
    List<ResumeSkill> findById_ResumeNo(Integer resumeNo);

    void deleteAllById_ResumeNo(int resumeNo);
}
