package org.example.restserver.repository;

import org.example.restserver.entity.Comment;
import org.example.restserver.entity.Community;
import org.example.restserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = """
            SELECT c
            FROM Comment c
            ORDER BY
                CASE
                    WHEN c.deleteYn = 'N' THEN 1
                    WHEN c.deleteYn = 'H' THEN 2
                    WHEN c.deleteYn = 'Y' THEN 3
                    ELSE 4
                END,
                c.createDate DESC
            """)
    List<Comment> findByOrderByDeleteYn();

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.deleteYn = :deleteYn WHERE c.id = :id")
    int updateDeleteYnById(int id, Character deleteYn);
}
