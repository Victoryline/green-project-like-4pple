package org.example.viewserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.ApplyResumeResponseDTO;
import org.example.viewserver.utils.WebClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2025-01-04 by 황승현
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/apply-resumes")
public class ApplyResumeController {
    private final WebClientManager webClientManager;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String applyResume(@RequestParam int jobPostNo, Model model) {
        List<?> applyResumes = (List<?>) webClientManager.get("/api/v1/apply-resumes/{jobPostNo}", jobPostNo).getBody();

        List<ApplyResumeResponseDTO> applyResumesDtos = objectMapper.convertValue(
                applyResumes, new TypeReference<>() {}
        );

        List<Integer> ageData = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        applyResumesDtos.forEach(data -> {
            int age = data.getAge();
            if (age >= 20 && age < 30) ageData.set(0, ageData.get(0) + 1);
            else if (age >= 30 && age < 40) ageData.set(1, ageData.get(1) + 1);
            else if (age >= 40 && age < 50) ageData.set(2, ageData.get(2) + 1);
            else if (age >= 50) ageData.set(3, ageData.get(3) + 1);
        });

        List<Integer> genderData = new ArrayList<>(Arrays.asList(0, 0));
        applyResumesDtos.forEach(data -> {
            String gender = data.getGender();
            if ("남자".equals(gender)) genderData.set(0, genderData.get(0) + 1);
            else if ("여자".equals(gender)) genderData.set(1, genderData.get(1) + 1);
        });

        model.addAttribute("applyResumes", applyResumes);
        model.addAttribute("ageData", ageData);
        model.addAttribute("genderData", genderData);
        model.addAttribute("jobPostNo", jobPostNo);

        return "/job-post/apply-resume-list";
    }
}
