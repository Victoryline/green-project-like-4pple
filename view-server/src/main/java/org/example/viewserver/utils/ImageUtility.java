package org.example.viewserver.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtility {

    public String loadImage(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            return "/static/images/logo.png";
        }

        try {
            byte[] binaryData = Base64.getDecoder().decode(base64String);

            if (binaryData.length < 4) {
                return "/static/images/logo.png";
            }

            int byte1 = Byte.toUnsignedInt(binaryData[0]);
            int byte2 = Byte.toUnsignedInt(binaryData[1]);
            int byte3 = Byte.toUnsignedInt(binaryData[2]);
            int byte4 = Byte.toUnsignedInt(binaryData[3]);

            String header = byte1 + "," + byte2 + "," + byte3 + "," + byte4;
            String mimeType;

            switch (header) {
                case "255,216,255,224": // JPEG 파일 서명
                case "255,216,255,225": // JPEG EXIF 서명
                    mimeType = "data:image/jpeg;base64,";
                    break;
                case "137,80,78,71": // PNG 파일 서명
                    mimeType = "data:image/png;base64,";
                    break;
                case "71,73,70,56": // GIF 파일 서명
                    mimeType = "data:image/gif;base64,";
                    break;
                default:
                    return "/static/images/logo.png";
            }

            return mimeType + base64String;
        } catch (IllegalArgumentException e) {
            return "/static/images/logo.png";
        }
    }
}
