package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class boardController {

    @GetMapping("/")
    public String board(){
        return "/board/community-regist";
    }
}
