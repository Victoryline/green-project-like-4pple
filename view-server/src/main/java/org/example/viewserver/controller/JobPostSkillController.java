package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.JobPostSkillResponseDto;
import org.example.viewserver.service.JobPostSkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : JobPostSkillController
 * author         : 이동하
 * date           : 2025-01-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-05        이동하       최초 생성
 */

@Controller
@RequestMapping("/jobpost")
@RequiredArgsConstructor
public class JobPostSkillController {

    private final JobPostSkillService jobPostSkillService;

    @GetMapping("/list1")
    public String list1() {
        return "job-post/list1";
    }

    @GetMapping("/detail1/{id}")
    public String detail1(@PathVariable Integer id, Model model) {
        JobPostSkillResponseDto jobPost = jobPostSkillService.getJobPostSkillDetail(id);
        model.addAttribute("jobPost", jobPost);
        return "job-post/detail1";
    }

}
