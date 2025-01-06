package org.example.restserver.entity;

import jakarta.persistence.*;
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

    @PrePersist
    public void prePersist() {
        if (likeDate == null) {
            likeDate = Instant.now();
        }
    }

}