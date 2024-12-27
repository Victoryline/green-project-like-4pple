package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
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

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

}