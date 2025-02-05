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
    private GubunId id; // 복합 키 클래스

    @Column(length = 50)
    private String name; // 'name' 컬럼

}
