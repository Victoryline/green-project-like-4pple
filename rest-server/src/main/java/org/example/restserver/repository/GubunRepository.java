package org.example.restserver.repository;

import org.example.restserver.dto.GubunDto;
import org.example.restserver.entity.Gubun;
import org.example.restserver.entity.GubunId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : GubunRepository
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

public interface GubunRepository extends JpaRepository<Gubun, GubunId> {
    @Query("SELECT g FROM Gubun g WHERE g.id.gubunCode = :gubunCode AND g.id.code = :code")
    Optional<Gubun> findByGubunCodeAndCode(@Param("gubunCode") String gubunCode, @Param("code") String code);

    List<Gubun> findAllByIdGubunCode(String gubunCode);

    @Query("SELECT g FROM Gubun g WHERE g.id.code = :code")
    Gubun findByCode(String code);
}
