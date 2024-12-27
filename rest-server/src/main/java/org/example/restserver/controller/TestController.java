package org.example.restserver.controller;

import org.example.restserver.common.ApiResponse;
import org.example.restserver.dto.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created on 2024-12-25 by 황승현
 */
@RestController
public class TestController {
    @GetMapping("/api/v1/test")
    public String test() throws Exception {
        return "메세지~~~";
    }
}
