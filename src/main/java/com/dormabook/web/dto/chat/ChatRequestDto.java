package com.dormabook.web.dto.chat;

import com.dormabook.domain.chat.Chat;
import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChatRequestDto {
    private Long chatNo;
    private StudyRoom studyroom;
    private String chatMemberid;
    private Timestamp chatCreatedat;
    private String chatContent;

    @Builder
    public ChatRequestDto(Long chatNo, StudyRoom studyroom, String chatMemberid, Timestamp chatCreatedat, String chatContent) {
        this.chatNo = chatNo;
        this.studyroom = studyroom;
        this.chatMemberid = chatMemberid;
        this.chatCreatedat = chatCreatedat;
        this.chatContent = chatContent;
    }

    public Chat toEntity(){
        return Chat.builder()
                .chatNo(chatNo)
                .studyroom(studyroom)
                .chatMemberid(chatMemberid)
                .chatCreatedat(chatCreatedat)
                .chatContent(chatContent)
                .build();
    }
}
