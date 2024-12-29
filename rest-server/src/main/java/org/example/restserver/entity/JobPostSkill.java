package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : org.example.restserver.repository
 * fileName       : JobPost
 * author         : 이동하
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        이동하       최초 생성
 */

@Data
@Getter
@Setter
@Entity
@Table(name = "tbl_job_post_skill")
public class JobPostSkill {

    @EmbeddedId
    private JobPostSkillId id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("jobPostNo") // 복합 키의 jobPostNo 필드를 부모 엔티티와 매핑
    @JoinColumn(name = "job_post_no", nullable = false)
    private JobPost jobPost;
}
