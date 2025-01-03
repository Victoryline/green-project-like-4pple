package org.example.restserver.repository;

import org.example.restserver.dto.IntroduceRequestDto;
import org.example.restserver.entity.Introduce;
import org.example.restserver.entity.IntroduceId;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : IntroduceRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface IntroduceRepository extends JpaRepository<Introduce, IntroduceId> {

    List<Introduce> findAllById_resumeNo(Integer resumeNo);

    void deleteAllById_ResumeNo(int resumeNo);
}
