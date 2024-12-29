package org.example.viewserver.common;

import jakarta.servlet.http.HttpServletRequest;
import org.example.viewserver.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/error-page"; // Error page template
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbiddenException(ForbiddenException ex) {
        return "redirect:/login";
    }
}
