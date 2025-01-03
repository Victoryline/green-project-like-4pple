package org.example.restserver.utils;

import org.example.restserver.entity.Gubun;
import org.example.restserver.repository.GubunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * packageName    : org.example.restserver.utils
 * fileName       : GubunUttil
 * author         : 김재홍
 * date           : 25. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 2.        김재홍       최초 생성
 */
@Component
public class GubunUttil {

    @Autowired
    private GubunRepository gubunRepository;

    public String getCode(String code){

        Gubun gubun =  gubunRepository.findByCode(code);

        return gubun.getName();
    }

}
