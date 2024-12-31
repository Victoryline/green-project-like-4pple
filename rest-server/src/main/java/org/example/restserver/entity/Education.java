package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.restserver.dto.EducationRequestDto;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_education")
public class Education {
    @Id
    @Column(name = "resume_no", nullable = false)
    private Integer id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume tblResume;

    @Column(name = "education_code", nullable = false, length = 20)
    private String educationCode;

    @Column(name = "school_name", nullable = false, length = 50)
    private String schoolName;

    @Column(name = "specialty", nullable = false, length = 50)
    private String specialty;

    @Column(name = "enter_date", nullable = false, length = 6)
    private String enterDate;

    @Column(name = "graduate_date", nullable = false, length = 6)
    private String graduateDate;

}