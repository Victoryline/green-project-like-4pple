package org.example.restserver.repository;

import org.example.restserver.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Community,Long> {

    post<Community> findAll
}
