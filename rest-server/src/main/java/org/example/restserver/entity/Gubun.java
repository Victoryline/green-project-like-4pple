package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_gubun")
public class Gubun {
    @Id
    private String gubunCode; // 'group_code' 컬럼

    private String code; // 'code' 컬럼은 Primary Key

    private String name;      // 'name' 컬럼


}