package com.dormabook.domain.chat;

import com.dormabook.domain.studyRoom.StudyRoom;
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
public class Chat {

    //자동인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNo;

    //Studyroom FK
    @OneToOne(targetEntity = StudyRoom.class)
    @JoinColumn(name = "studyroomNo")
    @Column(nullable = false)
    private StudyRoom studyroom;

    //발신자 아이디
    @Column(nullable = false)
    private String chatMemberid;

    //채팅 작성 날짜
    @Column(nullable = false)
    private Timestamp chatCreatedat;

    @Column(nullable = false)
    private String chatContent;

    @Builder
    public Chat(Long chatNo, StudyRoom studyroom, String chatMemberid, Timestamp chatCreatedat, String chatContent) {
        this.chatNo = chatNo;
        this.studyroom = studyroom;
        this.chatMemberid = chatMemberid;
        this.chatCreatedat = chatCreatedat;
        this.chatContent = chatContent;
    }
}
