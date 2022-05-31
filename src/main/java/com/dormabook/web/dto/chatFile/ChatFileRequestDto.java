package com.dormabook.web.dto.chatFile;

import com.dormabook.domain.chatFile.ChatFile;
import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatFileRequestDto {
    private Long chatfileNo;
    private StudyRoom studyroom;
    private String chatfileName;
    private String chatfileRoute;
    private String chatSavefilename;

    @Builder

    public ChatFileRequestDto(Long chatfileNo, StudyRoom studyroom, String chatfileName, String chatfileRoute, String chatSavefilename) {
        this.chatfileNo = chatfileNo;
        this.studyroom = studyroom;
        this.chatfileName = chatfileName;
        this.chatfileRoute = chatfileRoute;
        this.chatSavefilename = chatSavefilename;
    }

    public ChatFile toEntity(){
        return ChatFile.builder()
                .chatfileNo(chatfileNo)
                .studyroom(studyroom)
                .chatfileName(chatfileName)
                .chatfileRoute(chatfileRoute)
                .chatSavefilename(chatSavefilename)
                .build();
    }
}
