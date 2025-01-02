package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_comment")
public class Comment {

    @Column(name = "commnet_no")
    private int comment_no;

    @Column(name = "mommunity_no")
    private int community_no;

    @Column(name = "username")
    private String username;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate;

    @Column(name = "modify_date", nullable = false, updatable = false)
    private Instant modify_date;

}
