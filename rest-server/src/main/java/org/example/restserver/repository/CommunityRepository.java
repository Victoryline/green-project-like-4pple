package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.entity.Comment;
import org.example.restserver.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    List<Community> findByDeleteYn(Character deleteYn);

    List<Community> findAllByUsername(String username);

    String findByUsername(String username);

    @Query(value = """
            SELECT c
            FROM Community c
            ORDER BY
                CASE
                    WHEN c.deleteYn = 'N' THEN 1
                    WHEN c.deleteYn = 'H' THEN 2
                    WHEN c.deleteYn = 'Y' THEN 3
                    ELSE 4
                END,
                c.createDate DESC
            """)
    List<Community> findByOrderByDeleteYn();

    @Modifying
    @Transactional
    @Query("UPDATE Community c SET c.deleteYn = :deleteYn WHERE c.id = :id")
    int updateDeleteYnById(@Param("id") int id, @Param("deleteYn") Character deleteYn);
}
