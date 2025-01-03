package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : admin
 * author         : 황승현
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        황승현       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final WebClientManager webClientManager;

    @GetMapping("/user-management")
    public String userManagement(Model model) {
        model.addAttribute("jobSeekers", webClientManager.get("/api/v1/users/role-user?role=ROLE_JOB_SEEKER").getBody());
        model.addAttribute("companies", webClientManager.get("/api/v1/users/role-user?role=ROLE_COMPANY").getBody());
        return "admin/user-management";
    }

    @GetMapping("/board-management")
    public String boardManagement(Model model) {
        model.addAttribute("communities", webClientManager.get("/api/v1/boards/management/community").getBody());
        model.addAttribute("comments", webClientManager.get("/api/v1/boards/management/comment").getBody());

        System.out.println(webClientManager.get("/api/v1/boards/management/community").getBody());
        System.out.println(webClientManager.get("/api/v1/boards/management/comment").getBody());
        return "admin/board-management";
    }
}
