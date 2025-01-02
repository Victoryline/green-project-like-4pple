package org.example.restserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Community {

    @Column(name = "community_no")
    private int communityNo;

    @Column(name = "username")
    private String username;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "view_cnt")
    private int view_cnt;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate;

    @Column(name = "modify_date", nullable = false, updatable = false)
    private Instant modify_date;

    @Column(name = "delete_yn")
    private String delete_yn;
}
