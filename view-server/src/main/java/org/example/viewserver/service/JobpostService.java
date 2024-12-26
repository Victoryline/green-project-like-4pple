package org.example.viewserver.service;

import org.example.restserver.entity.JobPost;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * packageName    : org.example.viewserver.service
 * fileName       : JobpostService
 * author         : 박미정
 * date           : 24. 12. 26.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 26.        박미정      최초 생성
 */

public interface JobpostService{
    public ResponseEntity<?> regist(JobPost jobpost);
}
