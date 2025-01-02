package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.entity.Community;
import org.example.restserver.repository.CommunityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {
    private final CommunityRepository communityRepository;

    @GetMapping
    public List<Community> board() {
        return communityRepository.findByDeleteYn('N');
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
