package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.common.ApiResponse;
import org.example.viewserver.dto.CompanySearchDto;
import org.example.viewserver.dto.JobPostSearchDto;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : SearchController
 * author         : 이동하
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        이동하       최초 생성
 */
@Controller
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private WebClient webClient;

    private final WebClientManager webClientManager;

    @GetMapping("/search")
    public String searchResults(@RequestParam("type") String type, @RequestParam("keyword") String keyword,
            Model model) {
        var searchData = webClientManager.get("/api/v1/search?type=" + type + "&keyword=" + keyword).getBody();

        model.addAttribute("results", searchData);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "searchResults";
    }
}