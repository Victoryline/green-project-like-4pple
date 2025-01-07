package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.CompanyResponseDto;
import org.example.restserver.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : CompanyRestController
 * author         : 김재홍
 * date           : 25. 1. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 5.        김재홍       최초 생성
 */
@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyRestController {

    private final CompanyService companyService;

    @GetMapping("/detail/{username}")
    public ResponseEntity<CompanyResponseDto> getCompany(@PathVariable("username") String username){

//        log.info("asdf"+ companyService.getCompany(username));

        return ResponseEntity.ok(companyService.getCompany(username));
    }
}
