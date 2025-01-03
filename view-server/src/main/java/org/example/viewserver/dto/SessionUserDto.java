package org.example.viewserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created on 2024-12-29 by 황승현
 */
@Data
@AllArgsConstructor
public class SessionUserDto {
    private String username;
    private String name;
    private String role;
}

