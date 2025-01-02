package org.example.restserver.repository;

import org.example.restserver.entity.Education;
import org.example.restserver.entity.Military;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : MilitaryRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface MilitaryRepository extends JpaRepository<Military, Integer> {
}
