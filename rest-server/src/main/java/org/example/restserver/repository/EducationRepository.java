package org.example.restserver.repository;

import org.example.restserver.dto.EducationRequestDto;
import org.example.restserver.entity.Education;
import org.example.restserver.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : EducationRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface EducationRepository extends JpaRepository<Education, Integer> {
    Education findById(int resumeNo);
}
