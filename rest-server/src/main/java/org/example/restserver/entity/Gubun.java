package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_gubun")
public class Gubun {
    @EmbeddedId
    private GubunId id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

}