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
public class IntroduceId implements Serializable {
    private static final long serialVersionUID = 6457260034588477605L;
    @Column(name = "resume_no", nullable = false)
    private Integer resumeNo;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IntroduceId entity = (IntroduceId) o;
        return Objects.equals(this.resumeNo, entity.resumeNo) &&
                Objects.equals(this.title, entity.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resumeNo, title);
    }

}