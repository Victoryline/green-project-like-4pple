package org.example.viewserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/checck") // 기업이 제안서를 발송할 수 있는 구직자 리스트를 확인하는 페이지
    public String list(Model model/*, Resume resume*/) {
//        model.addAttribute("resume", resume);
        return "proposal/proposalList";
    }

}
