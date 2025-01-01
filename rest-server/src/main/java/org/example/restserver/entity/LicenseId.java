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
public class LicenseId implements Serializable {
    private static final long serialVersionUID = -8558387152904883913L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "license_name", nullable = false, length = 50)
    private String licenseName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LicenseId entity = (LicenseId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.licenseName, entity.licenseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, licenseName);
    }

}