package org.example.restserver.controller;

import org.example.restserver.dto.GubunDto;
import org.example.restserver.service.GubunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/gubun")
public class GubunRestController {

    @Autowired
    private final GubunService gubunService;

    public GubunRestController(GubunService gubunService) {
        this.gubunService = gubunService;
    }

    @GetMapping
    public List<GubunDto> getGubunList(@RequestParam String gubunCode) {
        List<GubunDto> gubunList = gubunService.getGubunList(gubunCode);

        return gubunList;
    }


}
