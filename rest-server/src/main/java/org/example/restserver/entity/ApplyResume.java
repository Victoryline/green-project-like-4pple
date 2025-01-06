package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_apply_resume")
public class ApplyResume {
    @EmbeddedId
    private ApplyResumeId id;

    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume resumeNo;

    @ColumnDefault("current_timestamp()")
    @Column(name = "apply_resume_date", nullable = false)
    private Instant applyResumeDate;

    @Column(name = "pass_yn")
    private Character passYn;

}