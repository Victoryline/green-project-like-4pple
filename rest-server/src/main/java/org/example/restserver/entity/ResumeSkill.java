package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tbl_resume_skill")
public class ResumeSkill {

    @EmbeddedId
    private ResumeSkillId id;

    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume resumeNo;

    @Override
    public String toString() {
        return "ResumeSkill{" +
                "id=" + id + ", " +
                "resumeNo=" + (resumeNo != null ? resumeNo.getId() : "null") +
                '}';
    }
}
