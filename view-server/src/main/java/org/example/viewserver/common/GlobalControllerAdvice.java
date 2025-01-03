package org.example.viewserver.common;

import lombok.RequiredArgsConstructor;
import org.example.viewserver.utils.SessionUserManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final SessionUserManager sessionUserManager;

    @ModelAttribute
    public void addUserToModel(Model model) {
        model.addAttribute("user", sessionUserManager.getUser());
    }
}
