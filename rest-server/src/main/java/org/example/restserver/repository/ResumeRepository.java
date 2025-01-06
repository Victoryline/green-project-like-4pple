package org.example.restserver.repository;

import org.example.restserver.dto.ResumeResponseDto;
import org.example.restserver.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : ResumeRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    List<Resume> findAllByUsername(String username);

    @Query("SELECT r FROM Resume r WHERE r.username = :username ORDER BY r.isPrimary DESC, r.createDate DESC")
    List<Resume> findAllByUserOrderByPrimaryAndDate(String username);

    // 특정 유저의 모든 대표 이력서 초기화
    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.isPrimary = 'N' WHERE r.username = :username")
    void resetPrimaryResume(@Param("username") String username);

    // 특정 이력서를 대표 이력서로 설정
    @Modifying
    @Transactional
    @Query("UPDATE Resume r SET r.isPrimary = 'Y' WHERE r.id = :resumeNo")
    void setPrimaryResume( @Param("resumeNo")Integer resumeNo);

}
