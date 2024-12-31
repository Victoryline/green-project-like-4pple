package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_activity")
public class Activity {
    @EmbeddedId
    private ActivityId id;

    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)
    private Resume resumeNo;

    @Column(name = "activity_center_name", length = 50)
    private String activityCenterName;

    @Lob
    @Column(name = "activity_content")
    private String activityContent;

    @Column(name = "start_date", length = 6)
    private String startDate;

    @Column(name = "end_date", length = 6)
    private String endDate;

}