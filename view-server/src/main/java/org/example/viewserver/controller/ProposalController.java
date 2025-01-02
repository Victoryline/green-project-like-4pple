package org.example.viewserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * packageName    : org.example.viewserver.controller
 * fileName       : ProposalController
 * author         : 이동하
 * date           : 2024-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-28        이동하       최초 생성
 */

@Controller
@RequestMapping("/proposal")
@RequiredArgsConstructor
public class ProposalController {

    private final WebClientManager webClientManager;



//    @GetMapping("/sendProposal")
//    public String proposalList(Model model) {
//        var proposals = webClientManager.get("/api/v1/proposals").getBody();
//        model.addAttribute("proposals", proposals);
//        System.out.println("프로포절@@@@@@@@@@@@@@@@@@@@"+proposals);
//        System.out.println("프로포절@@@@@@@@@@@@@@@@@@@@"+proposals);
//        System.out.println("프로포절@@@@@@@@@@@@@@@@@@@@"+proposals);
//        System.out.println("프로포절@@@@@@@@@@@@@@@@@@@@"+proposals);
//        System.out.println("프로포절@@@@@@@@@@@@@@@@@@@@"+proposals);
//
//        return "/proposal/sendProposal";
//    }

}