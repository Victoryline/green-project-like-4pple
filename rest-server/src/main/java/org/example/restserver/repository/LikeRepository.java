package org.example.restserver.repository;

import org.example.restserver.dto.BookmarkResponseDto;
import org.example.restserver.entity.Like;
import org.example.restserver.entity.LikeId;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : LikeRepository
 * author         : 김재홍
 * date           : 25. 1. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 6.        김재홍       최초 생성
 */
@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    @Query(value = "SELECT " +
            "c.username AS companyName, c.address AS companyAddress, c.profile_image AS companyImage " +
            "FROM tbl_like l " +
            "JOIN tbl_user u ON l.job_seeker_id = u.username " +
            "JOIN tbl_company c ON l.company_id = c.username " +
            "WHERE l.like_type = 'C' AND u.delete_yn = 'N' AND u.username = :jobSeekerId",
            nativeQuery = true)
    List<BookmarkResponseDto>  findBookmarksByJobSeekerIdNative(@Param("jobSeekerId") String jobSeekerId);

}
