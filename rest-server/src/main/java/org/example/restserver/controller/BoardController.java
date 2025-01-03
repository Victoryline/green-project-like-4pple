package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.entity.Comment;
import org.example.restserver.entity.Community;
import org.example.restserver.repository.CommentRepository;
import org.example.restserver.repository.CommunityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {
    private final CommunityRepository communityRepository;
    private final CommentRepository commentRepository;

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

    @GetMapping("/management/community")
    private List<Community> managementCommunity() {
        return communityRepository.findByOrderByDeleteYn();
    }

    @GetMapping("/management/comment")
    private List<Comment> managementComment() {
        return commentRepository.findByOrderByDeleteYn();
    }

    @PutMapping("/{type}")
    private int setDeleteYn(@PathVariable String type, @RequestParam int id, @RequestParam Character deleteYn) {
        if("community".equals(type)) {
            return communityRepository.updateDeleteYnById(id, deleteYn);
        } else if ("comment".equals(type)) {
            return commentRepository.updateDeleteYnById(id, deleteYn);
        }
        return 0;
    }
}
