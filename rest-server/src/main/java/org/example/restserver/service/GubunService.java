package org.example.restserver.service;

import org.example.restserver.dto.GubunDto;
import org.example.restserver.entity.Gubun;
import org.example.restserver.repository.GubunRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : org.example.restserver.service
 * fileName       : GubunService
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */
@Service
public class GubunService {
    @Autowired
    private GubunRepository gubunRepository;

    public Optional<Gubun> getSkillByCode(String code) {
        return gubunRepository.findByGubunCodeAndCode("SKILL", code);
    }


    public List<GubunDto> getAllGubun() {
        List<Gubun> gubunList = gubunRepository.findAll();
        List<GubunDto> gubunDtoList = new ArrayList<>();
        for (Gubun gubun : gubunList) {
            gubunDtoList.add(new GubunDto(gubun.getGubunCode(), gubun.getCode(), gubun.getName()));
        }
        return gubunDtoList;
    }
}
