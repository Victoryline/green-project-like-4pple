package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.SessionUserManager;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : BookmarkController
 * author         : 이동하
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        이동하       최초 생성
 */

@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final WebClientManager webClientManager;
    private final SessionUserManager sessionUserManager;

    @GetMapping("/byCompany")
    public String byCompany(Model model) {
        var bookmark = webClientManager.get("api/v1/proposal/bookmarked/" + sessionUserManager.getUsername()).getBody();
        model.addAttribute("bookmark", bookmark);
        return "bookmark/byCompany";
    }

    @GetMapping("/byJobSeeker")
    public String byJobSeeker() {
        return "bookmark/byJobSeeker";
    }
}
