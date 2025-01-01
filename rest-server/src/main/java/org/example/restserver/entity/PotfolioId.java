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
    private static final long serialVersionUID = 791098348972704600L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "potfolio_filename", nullable = false, length = 100)
    private String potfolioFilename;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PotfolioId entity = (PotfolioId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.potfolioFilename, entity.potfolioFilename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, potfolioFilename);
    }

}