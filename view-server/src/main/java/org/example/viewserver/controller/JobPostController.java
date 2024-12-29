package org.example.viewserver.controller;



import org.example.viewserver.dto.GubunDto;
import org.example.viewserver.service.GubunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/job-post")
public class JobPostController {

    @Autowired
    GubunService gubunService;

    @GetMapping("/regist")
    public String regist(Model model) {
        // 구분 데이터를 가져옵니다.
        List<GubunDto> gubunList = gubunService.getGubunList();
        model.addAttribute("gubunList", gubunList);

        // regist-form.html 뷰를 반환합니다.
        return "job-post/regist-form";
    }
/*
@GetMapping("/regist")
public String applicationRegist(Model model) {
    var careerResponse = apiService.fetchData("/api/gubn/career");
    var positionResponse = apiService.fetchData("/api/gubn/position");
    var educationResponse = apiService.fetchData("/api/gubn/education");
    var employmentResponse = apiService.fetchData("/api/gubn/employment");
    var stackResponse = apiService.fetchData("/api/gubn/stack");
    var stack1thResponse = apiService.fetchData("/api/gubn/stack1th");
    var applicationResponse = apiService.fetchData("/api/gubn/application");

        /*System.out.println(applicationResponse.getBody());
          System.out.println(positionResponse);

    var careerList =  careerResponse.getBody();
    var positionList = positionResponse.getBody();
    var educationList = educationResponse.getBody();
    var employmentList = employmentResponse.getBody();
    var stackList = stackResponse.getBody();
    var stack1thList = stack1thResponse.getBody();
    var applicationList = applicationResponse.getBody();

    model.addAttribute("careerList", careerList);
    model.addAttribute("positionList", positionList);
    model.addAttribute("educationList", educationList);
    model.addAttribute("employmentList", employmentList);
    model.addAttribute("stackList", stackList);
    model.addAttribute("stack1thList", stack1thList);
    model.addAttribute("applicationList", applicationList);

    return "/jeyeon/application-regist";
}*/


}