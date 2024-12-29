package org.example.viewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Fallback;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GubunDto {
    private String gubunCode;
    private String code;
    private String name;
}
