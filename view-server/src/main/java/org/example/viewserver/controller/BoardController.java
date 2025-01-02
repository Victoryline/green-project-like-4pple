package org.example.viewserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {



    @GetMapping ("/community")
        public String community () {
            return "/board/community";
        }
    @GetMapping("/regist-form")
    public String registForm() {
        return "/board/regist-form";
    }
    @GetMapping("/detail")
    public String detail(){
        return "/board/detail";
    }

}