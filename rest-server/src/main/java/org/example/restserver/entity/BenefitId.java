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
public class BenefitId implements Serializable {
    private static final long serialVersionUID = 3499955385506716524L;
    @Column(name = "job_post_no", nullable = false)
    private Integer jobPostNo;

    @Column(name = "benefit_content", nullable = false, length = 20)
    private String benefitContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BenefitId entity = (BenefitId) o;
        return Objects.equals(this.benefitContent, entity.benefitContent) &&
                Objects.equals(this.jobPostNo, entity.jobPostNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(benefitContent, jobPostNo);
    }

}