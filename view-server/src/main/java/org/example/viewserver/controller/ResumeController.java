package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : ResumeController
 * author         : 김재홍
 * date           : 25. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 2.        김재홍       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {

    @GetMapping("/list")
    public String list() {
        return "/resume/list";
    }

    @GetMapping("/reg")
    public String reg() {
        return "/resume/resume-regist";
    }

    @GetMapping("/view/{resumeNo}")
    public String view(@PathVariable("resumeNo") int resumeNo, Model model) {

        model.addAttribute("resumeNo", resumeNo);

        return "/resume/resume-detail";
    }

    @GetMapping("/modify/{resumeNo}")
    public String modify(@PathVariable("resumeNo") int resumeNo, Model model) {
        model.addAttribute("resumeNo", resumeNo);

        return "/resume/resume-modify";
    }

}
