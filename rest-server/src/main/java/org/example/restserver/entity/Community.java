package org.example.restserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_community")
public class Community {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_no", nullable = false)
    private Integer id;

    //    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "username", nullable = false)
//    private User username;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("0")
    @Column(name = "view_cnt", nullable = false)
    private Integer viewCnt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "modify_date")
    private Instant modifyDate;

    @ColumnDefault("'N'")
    @Column(name = "delete_yn")
    private Character deleteYn;




    @PrePersist
    public void onPrePersist() {
        if (this.createDate == null) {
            this.createDate = Instant.now();
        }
        if (this.deleteYn == null) {
            this.deleteYn = 'N';
        }
        if(this.viewCnt == null){
            this.viewCnt = 0;
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        if (this.modifyDate == null) {
            this.modifyDate = Instant.now();


        }
    }

}