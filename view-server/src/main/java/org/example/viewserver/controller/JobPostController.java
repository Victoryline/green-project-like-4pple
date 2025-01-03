package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.GubunDto;

import org.example.viewserver.dto.JobPostDto;
import org.example.viewserver.service.GubunService;
import org.example.viewserver.service.JobpostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final GubunService gubunService;
    private final JobpostServiceImpl jobpostService;


    @GetMapping("/regist")
    public String regist(Model model) {
        // 구분 데이터를 가져옵니다.
        List<GubunDto> workTypeList = gubunService.getGubunList("WORK_TYPE");
        List<GubunDto> jobRankList = gubunService.getGubunList("JOB_RANK");
        List<GubunDto> workList = gubunService.getGubunList("WORK");
        List<GubunDto> educationCodeList = gubunService.getGubunList("EDUCATION");
        List<GubunDto> skilList = gubunService.getGubunList("SKILL");
        model.addAttribute("workList", workList);
        model.addAttribute("workTypeList", workTypeList);
        model.addAttribute("jobRankList", jobRankList);
        model.addAttribute("educationCodeList", educationCodeList);
        model.addAttribute("skilList", skilList);
        // regist-form.html 뷰를 반환합니다.
        return "job-post/regist-form";
    }
    @GetMapping("/list")
    public String list() {
        return "job-post/list";
    }



    @GetMapping("/update-form/{jobpostno}")
    public String showJobPostDetails(@PathVariable Integer jobpostno, Model model) {
        JobPostDto jobPostDto = jobpostService.getJobPostDetail(jobpostno);
        if (jobPostDto == null) {
            return "error/404";  // 예시로 404 에러 페이지로 리다이렉트
        }
        model.addAttribute("jobPost", jobPostDto);
        return "job-post/update-form";
    }

}