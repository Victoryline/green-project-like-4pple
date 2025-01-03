package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tbl_like")
public class Like {
    @EmbeddedId
    private LikeId id;

    @Column(name = "like_date", nullable = false)
    private Instant likeDate;

}