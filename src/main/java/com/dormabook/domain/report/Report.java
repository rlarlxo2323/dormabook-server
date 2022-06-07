package com.dormabook.domain.report;

import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Report {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportNo;

    // 학습실 번호(인덱스)
    @ManyToOne(targetEntity = StudyRoom.class)
    @JoinColumn(name = "studyroomNo", nullable = false)
    private StudyRoom studyroom;

    // 파일 원본 이름
    @Column(nullable = false)
    private String reportFilename;

    // 파일 경로
    @Column(nullable = false)
    private String reportFileroute;

    // 저장 파일명
    @Column(nullable = false)
    private String reportSavefilename;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp reportSaveat;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String reportDownload;

    @Builder
    public Report(Long reportNo, StudyRoom studyroom, String reportFilename, String reportFileroute, String reportSavefilename, Timestamp reportSaveat, String reportDownload) {
        this.reportNo = reportNo;
        this.studyroom = studyroom;
        this.reportFilename = reportFilename;
        this.reportFileroute = reportFileroute;
        this.reportSavefilename = reportSavefilename;
        this.reportSaveat = reportSaveat;
        this.reportDownload = reportDownload;
    }
}