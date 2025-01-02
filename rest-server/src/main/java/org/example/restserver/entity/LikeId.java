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
public class LikeId implements Serializable {
    private static final long serialVersionUID = -8585176450399377769L;
    @Column(name = "job_seeker_id", nullable = false, length = 20)
    private String jobSeekerId;

    @Column(name = "company_id", nullable = false, length = 20)
    private String companyId;

    @Column(name = "like_type", nullable = false)
    private Character likeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LikeId entity = (LikeId) o;
        return Objects.equals(this.companyId, entity.companyId) &&
                Objects.equals(this.likeType, entity.likeType) &&
                Objects.equals(this.jobSeekerId, entity.jobSeekerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, likeType, jobSeekerId);
    }

}