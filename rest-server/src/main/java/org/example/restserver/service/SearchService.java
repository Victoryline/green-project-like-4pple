package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CompanySearchDto;
import org.example.restserver.dto.JobPostSearchDto;
import org.example.restserver.repository.JobPostRepository;
import org.example.restserver.repository.SearchRepository;
import org.example.restserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : HeaderSerch
 * author         : 이동하
 * date           : 2024-12-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-29        이동하       최초 생성
 */

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public List<CompanySearchDto> searchCompanies(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return searchRepository.findAllCompanies();
        }
        return searchRepository.findCompanyByKeyword(keyword);
    }

    public List<JobPostSearchDto> searchJobPosts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return searchRepository.findAllJobPosts();
        }
        return searchRepository.findJobPostByKeyword(keyword);
    }
}