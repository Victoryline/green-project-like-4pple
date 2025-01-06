package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.CommunityDto;
import org.example.restserver.entity.Comment;
import org.example.restserver.entity.Community;
import org.example.restserver.repository.CommentRepository;
import org.example.restserver.repository.CommunityRepository;
import org.springframework.web.bind.annotation.*;
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



    @PostMapping("/regist")
    public int register(@RequestBody CommunityDto communityDto) {

        try {
            Community community = Community.builder()
                    .username(communityDto.getUsername())
                    .title(communityDto.getTitle())
                    .content(communityDto.getContent())
                    .build();

            communityRepository.save(community);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("/detail")
    public Community detail(@RequestParam int id) {
        return communityRepository.findById(id).orElse(null);
    }

    @GetMapping("/mypost")
    public Community mypost(@RequestParam String username) {

        return communityRepository.findAllByUsername(username).stream().findFirst().orElse(null);
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
