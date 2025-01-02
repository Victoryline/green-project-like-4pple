package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private int comment_no;
    private int community_no;
    private String username;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;

}
