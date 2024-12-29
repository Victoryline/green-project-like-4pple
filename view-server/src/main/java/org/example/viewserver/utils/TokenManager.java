package org.example.viewserver.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 2024-12-29 by 황승현
 */
@Component
@RequiredArgsConstructor
public class TokenManager {
    private final HttpServletRequest request;

    /***
     *
     * @return token 반환
     */
    public String getTokenByCookie() {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> "token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
