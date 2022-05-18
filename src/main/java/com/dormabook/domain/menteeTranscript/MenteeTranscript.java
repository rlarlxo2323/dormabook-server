package com.dormabook.domain.menteeTranscript;

import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class MenteeTranscript {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menteetsNo;

    // 학습실 번호(인덱스)
    @OneToOne(targetEntity = StudyRoom.class)
    @JoinColumn(name = "studyroomNo", nullable = false)
    private StudyRoom studyroom;

    // 파일 원본 이름
    @Column(nullable = false)
    private String menteetsFilename;

    // 파일 경로
    @Column(nullable = false)
    private String menteetsFileroute;

    // 저장 파일명
    @Column(nullable = false)
    private String menteetsSavefilename;

    @Builder
    public MenteeTranscript(Long menteetsNo, StudyRoom studyroom, String menteetsFilename, String menteetsFileroute, String menteetsSavefilename) {
        this.menteetsNo = menteetsNo;
        this.studyroom = studyroom;
        this.menteetsFilename = menteetsFilename;
        this.menteetsFileroute = menteetsFileroute;
        this.menteetsSavefilename = menteetsSavefilename;
    }
}
