package org.example.restserver.service;

import org.example.restserver.dto.CompanyResponseDto;

/**
 * packageName    : org.example.restserver.service
 * fileName       : CompanyService
 * author         : 김재홍
 * date           : 25. 1. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 5.        김재홍       최초 생성
 */

public interface CompanyService {

    CompanyResponseDto getCompany(String username);

}
