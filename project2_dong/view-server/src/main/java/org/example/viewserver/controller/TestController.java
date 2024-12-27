package org.example.viewserver.controller;

import org.example.viewserver.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String test(Model model) {
        String msg = testService.getTestMessage();

        model.addAttribute("msg", msg);

        return "test";
    }
}
