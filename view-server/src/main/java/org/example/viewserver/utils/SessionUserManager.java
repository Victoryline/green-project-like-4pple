package org.example.viewserver.utils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.viewserver.dto.SessionUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 2025-01-01 by 황승현
 */
@Component
@RequiredArgsConstructor
public class SessionUserManager {
    private final HttpSession session;

    public SessionUserDto getUser() {
        return (SessionUserDto) session.getAttribute("user");
    }

    public String getUsername() {
        return isNullUser() ? "" : ((SessionUserDto) session.getAttribute("user")).getUsername();
    }

    public String getName() {
        return isNullUser() ? "" : ((SessionUserDto) session.getAttribute("user")).getName();
    }

    public String getRole() {
        return isNullUser() ? "" : ((SessionUserDto) session.getAttribute("user")).getRole();
    }

    private boolean isNullUser() {
        return session.getAttribute("user") == null;
    }

    public void removeUser() {
        session.removeAttribute("user");
    }
}
