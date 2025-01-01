package org.example.restserver.repository;

import org.example.restserver.entity.Activity;
import org.example.restserver.entity.ActivityId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : ActivityRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface ActivityRepository extends JpaRepository<Activity, ActivityId> {
}
