package org.example.restserver.utils;

import org.example.restserver.dto.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * packageName    : org.example.restserver.utils
 * fileName       : FileUtil
 * author         : 김재홍
 * date           : 24. 12. 26.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 12. 26.        김재홍       최초 생성
 */
@Component
public class FileUtil {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; //10MB 제한

    public FileDto saveFile(MultipartFile file) throws IOException {

        if(file.getSize() > MAX_FILE_SIZE){
            throw new IOException("파일 크기가 너무 큽니다. 최대 허용 크기는 10MB 입니다.");
        }

        String originalFilename = file.getOriginalFilename();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = originalFilename+ "_" + timestamp;

        FileDto fileDto = new FileDto();
        fileDto.setFilename(fileName);
        fileDto.setImage(file.getBytes());

        return fileDto;

    }

}
