package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no", nullable = false)
    private Integer id;

    @Column(name = "community_no", nullable = false)
    private Integer communityNo;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "modify_date")
    private Instant modifyDate;

    @ColumnDefault("'N'")
    @Column(name = "delete_yn")
    private Character deleteYn;



}