package org.example.restserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.BookmarkResponseDto;
import org.example.restserver.dto.LikeRequestDto;
import org.example.restserver.entity.Like;
import org.example.restserver.repository.LikeRepository;
import org.example.restserver.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : BookmarkRestController
 * author         : 김재홍
 * date           : 25. 1. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 6.        김재홍       최초 생성
 */
@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
@Slf4j
public class BookmarkRestController {

    private final LikeService likeService;

    @PostMapping("/save")
    public void addBookmark(@RequestBody LikeRequestDto request) {

        likeService.saveBookmark(request);

    }

    @GetMapping("/list")
    public ResponseEntity<List<BookmarkResponseDto>> getBookmarkCount(@RequestParam String seeker) {

        log.info("select get | "+likeService.getBookMardList(seeker));

        return ResponseEntity.ok(likeService.getBookMardList(seeker));
    }

    @GetMapping("/status")
    public Boolean getBookmark(@RequestParam String seeker, @RequestParam String username) {
        log.info("select get | "+likeService.getBookmark(seeker, username));

        return likeService.getBookmark(seeker, username);

    }

    @DeleteMapping("/delete")
    public Boolean deleteBookmark(@RequestParam String seeker, @RequestParam String username) {
        log.info("deleteBookmark request : {}", seeker);

        return likeService.deleteBookmark(seeker, username);

    }


}
