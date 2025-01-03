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
public class PotfolioId implements Serializable {
    private static final long serialVersionUID = 2610697308751161154L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "portfolio_filename", nullable = false, length = 100)
    private String portfolioFilename;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PotfolioId entity = (PotfolioId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.portfolioFilename, entity.portfolioFilename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, portfolioFilename);
    }

}