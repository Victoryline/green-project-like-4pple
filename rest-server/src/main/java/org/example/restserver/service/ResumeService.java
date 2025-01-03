package org.example.restserver.service;

import org.example.restserver.dto.JobSeekerUserResponseDto;
import org.example.restserver.dto.ResumeRequestDto;
import org.example.restserver.dto.ResumeResponseDto;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : ResumeService
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface ResumeService {

    int postResume(ResumeRequestDto resumeDto);

    JobSeekerUserResponseDto getUserInfo(String username);

    ResumeResponseDto getResumeInfo(int resumeNo);

    List<ResumeResponseDto> getAllResumes(String username);

    void deleteResume(int resumeNo);
}
