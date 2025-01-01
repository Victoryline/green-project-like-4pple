package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_introduce")
public class Introduce {
    @EmbeddedId
    private IntroduceId id;

    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume resumeNo;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "ord", nullable = false)
    private Integer ord;

}