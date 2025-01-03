package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

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

    private final WebClientManager webClientManager;

    @GetMapping("/search")
    public String searchResults(@RequestParam("type") String type, @RequestParam("keyword") String keyword,
            Model model) {

        if ((keyword == null || keyword.trim().isEmpty()) && "job".equals(type)) {
            return "job-post/list1";
        }

        var searchData = webClientManager.get("/api/v1/search?type=" + type + "&keyword=" + keyword).getBody();
        System.out.println(searchData);
        model.addAttribute("results", searchData);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

            return "searchResults";

    }
}