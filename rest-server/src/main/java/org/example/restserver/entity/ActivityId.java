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
public class ActivityId implements Serializable {
    private static final long serialVersionUID = 9102900911615210045L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "activity_type", nullable = false, length = 20)
    private String activityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActivityId entity = (ActivityId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.activityType, entity.activityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, activityType);
    }

}