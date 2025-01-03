package org.example.restserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDto {

    private int community_no; //게시글번호
    private String username; //작성자 이름
    private String title; //게시글 제목
    private String content; //게시글 내용
    private int view_cnt; //조회수
    private LocalDateTime create_time; //작성시간
    private LocalDateTime modify_time; //수정시간


}
