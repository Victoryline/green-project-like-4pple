package org.example.restserver.repository;

import org.apache.ibatis.annotations.Param;
import org.example.restserver.dto.ApplyResumeResponseDTO;
import org.example.restserver.entity.ApplyResume;
import org.example.restserver.entity.ApplyResumeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2025-01-04 by 황승현
 */
public interface ApplyResumeRepository extends JpaRepository<ApplyResume, ApplyResumeId> {
    @Query(value =
            """
                    SELECT
                        ar.job_post_no AS jobPostNo,
                        u.name AS userName,
                        js.birth AS birth,
                        CASE
                            WHEN js.gender = 'M' THEN '남자'
                            WHEN js.gender = 'F' THEN '여자'
                            ELSE '기타'
                        END AS gender,
                        TIMESTAMPDIFF(YEAR, js.birth, CURRENT_DATE) AS age,
                        ar.resume_no AS resumeNo,
                        r.title AS title,
                        ar.apply_resume_date AS applyResumeDate,
                        co.username AS companyId,
                        ar.pass_yn
                    FROM tbl_apply_resume ar
                    JOIN tbl_resume r ON r.resume_no = ar.resume_no
                    JOIN tbl_user u ON r.username = u.username AND u.delete_yn = 'N'
                    JOIN tbl_job_seeker js ON r.username = js.username
                    JOIN tbl_job_post jp ON ar.job_post_no = jp.job_post_no
                    JOIN tbl_company co ON jp.username = co.username
                    WHERE ar.job_post_no = :jobPostNo
                    ORDER BY
                    CASE WHEN pass_yn = 'H' THEN 0
                    WHEN pass_yn = 'Y' THEN 1
                    WHEN pass_yn = 'N' THEN 2
                    END,
                    ar.apply_resume_date
                    """,
            nativeQuery = true)
    List<Object[]> findApplyResumeByJobPostNo(int jobPostNo);

    default List<ApplyResumeResponseDTO> getApplicationsByJobPostNo(int jobPostNo) {
        return findApplyResumeByJobPostNo(jobPostNo).stream()
                .map(row -> ApplyResumeResponseDTO.builder()
                        .jobPostNo(((Number) row[0]).intValue())
                        .name((String) row[1])
                        .birth((Date) row[2])
                        .gender((String) row[3])
                        .age(((Number) row[4]).intValue())
                        .resumeNo(((Number) row[5]).intValue())
                        .title((String) row[6])
                        .applyResumeDate(((Timestamp) row[7]).toInstant())
                        .companyId((String) row[8])
                        .passYn((Character) row[9])
                        .build())
                .collect(Collectors.toList());
    }

    @Modifying
    @Transactional
    @Query("UPDATE ApplyResume ar SET ar.passYn = :passYn WHERE ar.id.jobPostNo = :jobPostNo AND ar.id.resumeNo = :resumeNo")
    int updatePassYnByJobPostNoAndResumeNo(@Param("jobPostNo") int jobPostNo,@Param("resumeNo") int resumeNo, @Param("passYn") Character passYn);

}
