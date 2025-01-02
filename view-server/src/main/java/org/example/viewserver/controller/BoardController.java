package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final WebClientManager webClientManager;

    @GetMapping("/community")
    public String community(Model model) {
        model.addAttribute("communities", webClientManager.get("/api/v1/boards").getBody());
        return "/board/community";
    }

    @GetMapping("/regist-form")
    public String registForm() {
        return "/board/regist-form";
    }

    @GetMapping("/detail")
    public String detail() {
        return "/board/detail";
    }

}