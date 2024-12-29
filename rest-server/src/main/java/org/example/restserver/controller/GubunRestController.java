package org.example.restserver.controller;

import org.example.restserver.dto.GubunDto;
import org.example.restserver.service.GubunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/gubun")
public class GubunRestController {

    @Autowired
    private final GubunService gubunService;



    public GubunRestController(GubunService gubunService) {
        this.gubunService = gubunService;
    }

    @GetMapping("/getgubun")
    public List<GubunDto> getGubunList() {

        return (List<GubunDto>) gubunService.getAllGubun();
    }
}
