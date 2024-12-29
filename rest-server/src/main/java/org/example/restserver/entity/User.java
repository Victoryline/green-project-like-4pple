package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "role", length = 20)
    private String role;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @Column(name = "delete_yn")
    private String deleteYn;

    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = Instant.now();
        }
        if (deleteYn == null) {
            deleteYn = "N";
        }
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = Instant.now();
    }

}