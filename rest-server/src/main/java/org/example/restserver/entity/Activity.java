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
    private ActivityId id;  // 복합 키로 ActivityId 사용

    // 연관 관계 설정: resumeNo는 ActivityId 내에 포함되므로 별도의 필드는 필요 없음
    @MapsId("resumeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_no", nullable = false)  // ActivityId의 resumeNo와 매핑    private Resume resume;  // ActivityId 내 resumeNo와 연관된 Resume 엔티티
    private Resume resumeNo;

    @Column(name = "activity_center_name", nullable = false, length = 50)
    private String activityCenterName;

    @Lob
    @Column(name = "activity_content", nullable = false)
    private String activityContent;

    @Column(name = "start_date", nullable = false, length = 6)
    private String startDate;

    @Column(name = "end_date", nullable = false, length = 6)
    private String endDate;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id + ", " + // 복합 키 (ActivityId)
                "resumeNo=" + (resumeNo != null ? resumeNo.getId() : "null") + ", " + // Resume 엔티티와의 연관 (id로 출력)
                "activityCenterName='" + activityCenterName + '\'' + ", " + // 활동 센터 이름
                "activityContent='" + activityContent + '\'' + ", " + // 활동 내용
                "startDate='" + startDate + '\'' + ", " + // 시작 날짜
                "endDate='" + endDate + '\'' + // 종료 날짜
                '}';
    }
}
