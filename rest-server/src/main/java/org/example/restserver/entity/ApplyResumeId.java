package org.example.restserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ApplyResumeId implements Serializable {
    private static final long serialVersionUID = -1341970123347975810L;
    @Column(name = "job_post_no", nullable = false)
    private Integer jobPostNo;

    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplyResumeId entity = (ApplyResumeId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.jobPostNo, entity.jobPostNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, jobPostNo);
    }

}