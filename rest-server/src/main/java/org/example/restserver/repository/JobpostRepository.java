package org.example.restserver.repository;

import org.example.restserver.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobpostRepository
 * author         : 박미정
 * date           : 24. 12. 26.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 26.        박미정      최초 생성
 */
@Repository
public interface JobpostRepository extends JpaRepository<JobPost, Integer> {


}
