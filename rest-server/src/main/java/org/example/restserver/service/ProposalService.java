package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.ProposalRequestDto;
import org.example.restserver.dto.ProposalResponseDto;
import org.example.restserver.entity.Proposal;
import org.example.restserver.entity.Resume;
import org.example.restserver.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : org.example.restserver.service
 * fileName       : ProposalService
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    //기업이 북마크한 구직자 조회
    public List<String> getBookmarkedJobSeeker(String companyId){
        return proposalRepository.findBookmarkedJobSeeker(companyId);
    }
    //북마크한 전체 구직자의 이력서 조회
    public List<Resume> getResumes(String companyId){
        List<String> user = proposalRepository.findBookmarkedJobSeeker(companyId);
        return proposalRepository.findResume(user);
    }
    //하나의 이력서 상세정보 조회
    public Resume getResumeDetails(Integer resumeId){
        return proposalRepository.findResumeDetail(resumeId);
    }
}