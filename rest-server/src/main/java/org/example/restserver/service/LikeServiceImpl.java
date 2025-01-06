package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.restserver.dto.BookmarkResponseDto;
import org.example.restserver.dto.LikeRequestDto;
import org.example.restserver.entity.Like;
import org.example.restserver.entity.LikeId;
import org.example.restserver.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * packageName    : org.example.restserver.service
 * fileName       : LikeServiceImpl
 * author         : 김재홍
 * date           : 25. 1. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 6.        김재홍       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public void saveBookmark(LikeRequestDto request) {

        Like like = new Like();
        LikeId id = new LikeId();
        id.setJobSeekerId(request.getSeeker());
        id.setCompanyId(request.getUsername());
        id.setLikeType('C');
        like.setId(id);
        like.setLikeDate(Instant.now());

        log.info("Saving bookmark: {}", request);
        log.info("Generated LikeId: {}", id);
        log.info("Bookmark exists: {}", likeRepository.existsById(id));

        likeRepository.save(like);
    }

    @Override
    public Boolean getBookmark(String seeker, String username) {

        LikeId likeId = new LikeId();

        likeId.setLikeType('C');
        likeId.setCompanyId(username);
        likeId.setJobSeekerId(seeker);

        log.info("Generated LikeId: {}", likeId);
        log.info("Bookmark exists: {}", likeRepository.existsById(likeId));

//        return likeRepository.findById(likeId).orElse(null);
        return likeRepository.existsById(likeId);
    }

    @Override
    public Boolean deleteBookmark(String seeker, String username) {

        LikeId likeId = new LikeId();
        likeId.setLikeType('C');
        likeId.setCompanyId(username);
        likeId.setJobSeekerId(seeker);

//        log.info("Saving bookmark: {}", request);
        log.info("Generated LikeId: {}", likeId);
        log.info("Bookmark exists: {}", likeRepository.existsById(likeId));

        likeRepository.deleteById(likeId);

        return likeRepository.existsById(likeId);

    }

    @Override
    public List<BookmarkResponseDto> getBookMardList(String seeker) {

        return likeRepository.findBookmarksByJobSeekerIdNative(seeker);

    }


}
