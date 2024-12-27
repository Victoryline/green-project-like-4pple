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
public class JobPostSkillId implements Serializable {
    private static final long serialVersionUID = -271639146864305665L;
    @Column(name = "job_post_no", nullable = false)
    private Integer jobPostNo;

    @Column(name = "skill_code", nullable = false, length = 20)
    private String skillCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobPostSkillId entity = (JobPostSkillId) o;
        return Objects.equals(this.skillCode, entity.skillCode) &&
                Objects.equals(this.jobPostNo, entity.jobPostNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillCode, jobPostNo);
    }

}