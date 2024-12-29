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
public class GubunId implements Serializable {
    private static final long serialVersionUID = 3631477017387073265L;
    @Column(name = "gubun_code", nullable = false, length = 20)
    private String gubunCode;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GubunId entity = (GubunId) o;
        return Objects.equals(this.gubunCode, entity.gubunCode) &&
                Objects.equals(this.code, entity.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gubunCode, code);
    }
}