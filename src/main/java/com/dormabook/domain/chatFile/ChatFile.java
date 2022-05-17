package com.dormabook.domain.chatFile;

import com.dormabook.domain.studyRoom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class ChatFile {

    //자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatfileNo;

    //StudyRoom FK
    @ManyToOne(targetEntity = StudyRoom.class)
    @JoinColumn(name = "studyroomNo")
    private StudyRoom studyroom;

    //파일 원본 이름
    @Column(nullable = false)
    private String chatfileName;

    //파일 경로
    @Column(nullable = false)
    private String chatfileRoute;

    //저장 파일명
    @Column(nullable = false)
    private String chatSavefilename;

    @Builder
    public ChatFile(Long chatfileNo, StudyRoom studyroom, String chatfileName, String chatfileRoute, String chatSavefilename) {
        this.chatfileNo = chatfileNo;
        this.studyroom = studyroom;
        this.chatfileName = chatfileName;
        this.chatfileRoute = chatfileRoute;
        this.chatSavefilename = chatSavefilename;
    }
}
