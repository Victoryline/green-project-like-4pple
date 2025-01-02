package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class BoardController {

    @GetMapping("/")
    public String board() {
        return "/board/community-regist";
    }


    @GetMapping("/detail")
    public String detail() {

        return "/board/detail";
    }

    @GetMapping("/community")
    public String community() {

        return "/board/community";
    }
}
