package org.example.restserver.repository;

import org.example.restserver.entity.License;
import org.example.restserver.entity.LicenseId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : LicenseRepository
 * author         : 김재홍
 * date           : 24. 12. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 30.        김재홍       최초 생성
 */
public interface LicenseRepository extends JpaRepository<License, LicenseId> {
}
