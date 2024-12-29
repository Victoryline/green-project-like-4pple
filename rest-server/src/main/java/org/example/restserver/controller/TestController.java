package org.example.restserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2024-12-25 by 황승현
 */
@RestController
public class TestController {
    @GetMapping("/api/v1/test")
    public String test() {
        return "메세지~~~";
    }
}
