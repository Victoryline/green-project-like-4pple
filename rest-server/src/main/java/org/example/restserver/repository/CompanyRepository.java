package org.example.restserver.repository;

import org.example.restserver.entity.Company;
import org.example.restserver.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : UserRepository
 * author         : 황승현
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        황승현       최초 생성
 */
public interface CompanyRepository extends JpaRepository<Company, String> {
}
