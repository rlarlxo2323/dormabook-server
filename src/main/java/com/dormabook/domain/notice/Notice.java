package com.dormabook.domain.notice;

import com.dormabook.domain.application.Application;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Entity
@NoArgsConstructor
@Component
public class Notice {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;

    // 신청서 번호(인덱스)
    @OneToOne(targetEntity = Application.class)
    @JoinColumn(name = "applicationNo", nullable = false)
    private Application application;

    // 신청한 날짜
    @Column(nullable = false)
    private Timestamp noticecreateAt;

    @Builder
    public Notice(Long noticeNo, Application application, Timestamp noticecreateAt) {
        this.noticeNo = noticeNo;
        this.application = application;
        this.noticecreateAt = noticecreateAt;
    }
}
