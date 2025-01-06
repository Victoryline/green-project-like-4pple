package org.example.restserver.service;

import org.example.restserver.dto.BookmarkResponseDto;
import org.example.restserver.dto.LikeRequestDto;
import org.example.restserver.entity.Like;

import java.util.List;

/**
 * packageName    : org.example.restserver.service
 * fileName       : LikeService
 * author         : 김재홍
 * date           : 25. 1. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 6.        김재홍       최초 생성
 */
public interface LikeService {

    void saveBookmark(LikeRequestDto request);

    Boolean getBookmark(String seeker, String username);

    Boolean deleteBookmark(String seeker, String username);

    List<BookmarkResponseDto> getBookMardList(String seeker);
}
