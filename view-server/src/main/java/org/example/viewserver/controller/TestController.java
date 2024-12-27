package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final WebClientUtil webClientUtil;

    @GetMapping
    public String test(Model model) {
        var apiResponseMsg = webClientUtil.get("/api/v1/test");
        model.addAttribute("msg", apiResponseMsg.getBody());
        return "test";
    }

//    @GetMapping
//    public String main(Model model) {
//        return "/company/regist-form";
//    }

    @GetMapping("/regist")
    public String regist() {
        return "/resume/resume-regist";
    }
}
