package com.dormabook.web.dto.menteeTranscript;

import com.dormabook.domain.menteeTranscript.MenteeTranscript;
import com.dormabook.domain.studyroom.StudyRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenteeTranscriptRequestDto {
    private Long menteetsNo;
    private StudyRoom studyroom;
    private String menteetsFilename;
    private String menteetsFileroute;
    private String menteetsSavefilename;

    @Builder

    public MenteeTranscriptRequestDto(Long menteetsNo, StudyRoom studyroom, String menteetsFilename, String menteetsFileroute, String menteetsSavefilename) {
        this.menteetsNo = menteetsNo;
        this.studyroom = studyroom;
        this.menteetsFilename = menteetsFilename;
        this.menteetsFileroute = menteetsFileroute;
        this.menteetsSavefilename = menteetsSavefilename;
    }

    public MenteeTranscript toEntity(){
        return MenteeTranscript.builder()
                .menteetsNo(menteetsNo)
                .studyroom(studyroom)
                .menteetsFilename(menteetsFilename)
                .menteetsFileroute(menteetsFileroute)
                .menteetsSavefilename(menteetsSavefilename)
                .build();
    }
}
