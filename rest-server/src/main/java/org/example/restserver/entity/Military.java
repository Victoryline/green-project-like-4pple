package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_military")
public class Military {
    @Id
    @Column(name = "resume_no", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume tblResume;

    @Column(name = "military_code", nullable = false, length = 20)
    private String militaryCode;

    @Column(name = "enlist_date", length = 6)
    private String enlistDate;

    @Column(name = "release_date", length = 6)
    private String releaseDate;

}