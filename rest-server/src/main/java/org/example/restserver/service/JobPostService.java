package org.example.restserver.service;

import org.example.restserver.entity.JobPost;
import org.example.restserver.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : JobPostService
 * author         : 이동하
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        이동하       최초 생성
 */
@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    public List<JobPost> findByWorkCode(String workCode) {
        return jobPostRepository.findByWorkCode(workCode);
    }

}
