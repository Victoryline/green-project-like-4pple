package org.example.restserver.controller;

import org.example.restserver.entity.JobSkill;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : org.example.restserver.controller
 * fileName       : JobSkillController
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@RestController
@RequestMapping("/jobPost")
public class JobSkillController {

    @PostMapping("/match")
    public ResponseEntity<?> match(@RequestBody JobSkill jobSkill) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
