package org.example.restserver.utils;

/**
 * packageName    : org.example.restserver.utils
 * fileName       : ConvertTokenUtil
 * author         : 황승현
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        황승현       최초 생성
 */
public class ConvertTokenUtil {
    public static String bearerExcludeToken(String token) {
        return token.substring(7);
    }
}
