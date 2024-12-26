package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * packageName    : org.example.restserver.entity
 * fileName       : JobSkill
 * author         : 이동하
 * date           : 2024-12-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-26        이동하       최초 생성
 */
@Entity
@Table(name = "tbl_job_post_skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSkill {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_post_no")
    private JobPost jobPost;

    @Column(name = "skill_code")
    private String skillCode;

}

