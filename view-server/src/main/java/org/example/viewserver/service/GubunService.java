package org.example.viewserver.service;


import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.GubunDto;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GubunService {

    private final WebClientManager webClientManager;

    public List<GubunDto> getGubunList(String gubunCode)  {
        List<GubunDto> gubnList = (List<GubunDto>) webClientManager.get("/api/v1/gubun?gubunCode=" + gubunCode).getBody();
//        System.out.print("gubnList: " + gubnList);
        // 레스트 서버 API 호출
        return gubnList;
    }


    //등록


}