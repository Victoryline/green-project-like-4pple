package org.example.restserver.repository;

import org.example.restserver.entity.Activity;
import org.example.restserver.entity.ActivityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, ActivityId> {

    // ActivityId를 기준으로 resumeNo에 해당하는 모든 Activity 조회
    List<Activity> findById_ResumeNo(Integer resumeNo);  // ActivityId 내 resumeNo 사용

    void deleteById_ResumeNo(int resumeNo);
}
