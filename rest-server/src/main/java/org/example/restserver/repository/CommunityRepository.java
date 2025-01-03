package org.example.restserver.repository;

import org.example.restserver.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommunityRepository extends JpaRepository<Community, Integer> {


    List<Community> findByDeleteYn(Character deleteYn);

    List<Community> findAllByUsername(String username);
    String findByUsername(String username);
}


