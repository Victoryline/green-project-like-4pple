package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : CompanyController
 * author         : 김재홍
 * date           : 25. 1. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 5.        김재홍       최초 생성
 */
@Controller
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    @GetMapping("/{username}")
    public String getCompany(@PathVariable("username") String username, Model model){

        model.addAttribute("username", username);

        return "/company/detail";
    }

}
