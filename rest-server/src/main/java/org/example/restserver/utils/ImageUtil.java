package org.example.restserver.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtil {
    /***
     * Base64인코딩 함수
     * @param imageBytes : 이미지 바이트 배열
     * @return : Base64로 인코딩된 문자열
     */
    public static String encodeToBase64(byte[] imageBytes) {
        if(imageBytes == null) return "";
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    /***
     * Base64 디코딩 함수
     * @param base64String : Base64 문자열
     * @return : 디코딩된 바이트 배열
     */
    public static byte[] decodeFromBase64(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            System.out.println("Base64 string is null or empty");
            return null;
        }
        try {
            return Base64.getDecoder().decode(base64String);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Base64 string: " + base64String);
            throw new RuntimeException("Base64 decoding failed", e);
        }
    }
}