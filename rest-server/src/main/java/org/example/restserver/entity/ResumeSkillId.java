package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ResumeSkillId implements Serializable {
    private static final long serialVersionUID = 6932904425807367602L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "skill_code", nullable = false, length = 20)
    private String skillCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResumeSkillId entity = (ResumeSkillId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.skillCode, entity.skillCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, skillCode);
    }

}