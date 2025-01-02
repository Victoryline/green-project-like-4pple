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
public class CompanyScoreId implements Serializable {
    private static final long serialVersionUID = 4724558537081994982L;
    @Column(name = "company_id", nullable = false, length = 20)
    private String companyId;

    @Column(name = "job_seeker_id", nullable = false, length = 20)
    private String jobSeekerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompanyScoreId entity = (CompanyScoreId) o;
        return Objects.equals(this.companyId, entity.companyId) &&
                Objects.equals(this.jobSeekerId, entity.jobSeekerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, jobSeekerId);
    }

}