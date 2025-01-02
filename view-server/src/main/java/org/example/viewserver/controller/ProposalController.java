package org.example.viewserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class ProposalController {

    @RequestMapping("/proposalList")
    public String proposalList(){
        return "/proposal/proposalList";
    }

}