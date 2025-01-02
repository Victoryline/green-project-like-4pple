package org.example.viewserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : MypageController
 * author         : 이동하
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        이동하       최초 생성
 */

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @RequestMapping("/jobseekerMypage")
    public String jobseekerMypage() {
        return "/mypage/jobseekerMypage";
    }

    @RequestMapping("/companyMypage")
    public String companyMypage() {
        return "/mypage/companyMypage";
    }
}
