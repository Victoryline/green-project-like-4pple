package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public Mono<String> test(Model model) {
        return testService.getTestMessage()
                .map(message -> {
                    model.addAttribute("msg", message.toString());
                    return "test";
                });
    }
}
