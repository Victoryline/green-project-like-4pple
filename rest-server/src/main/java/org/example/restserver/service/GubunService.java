package org.example.restserver.service;

import lombok.RequiredArgsConstructor;
import org.example.restserver.dto.GubunDto;
import org.example.restserver.entity.Gubun;
import org.example.restserver.repository.GubunRepository;

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
@RequiredArgsConstructor
public class GubunService {
    private final GubunRepository gubunRepository;

    public Optional<Gubun> getSkillByCode(String code) {
        return gubunRepository.findByGubunCodeAndCode("SKILL", code);
    }

    public List<GubunDto> getGubunList(String gubunCode) {
        List<GubunDto> gubunDtoList = new ArrayList<>();
//        List<GubunDto> gubunList = gubunRepository.findAllByIdGubunCode(gubunCode)
//                .stream().map(gubun -> new GubunDto(gubun.getId().getGubunCode(), gubun.getId().getCode(), gubun.getName())
//                ).toList();

        List<Gubun> gubunList = gubunRepository.findAllByIdGubunCode(gubunCode);

        for (Gubun gubun : gubunList) {
            gubunDtoList.add(new GubunDto(gubun.getId().getGubunCode(), gubun.getId().getCode(), gubun.getName()));
        }

        return gubunDtoList;
    }

}
