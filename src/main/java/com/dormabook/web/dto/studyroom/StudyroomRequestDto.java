package com.dormabook.web.dto.studyroom;

import com.dormabook.domain.studyroom.StudyRoom;
import com.dormabook.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyroomRequestDto {
    private Long studyroomNo;
    private Team team;
    private String studyroomdomainaddr;
    private String studyroomMenteegrade;
    private String studyroomSubject;

    @Builder
    public StudyroomRequestDto(Long studyroomNo, Team team, String studyroomdomainaddr, String studyroomMenteegrade, String studyroomSubject) {
        this.studyroomNo = studyroomNo;
        this.team = team;
        this.studyroomdomainaddr = studyroomdomainaddr;
        this.studyroomMenteegrade = studyroomMenteegrade;
        this.studyroomSubject = studyroomSubject;
    }

    public StudyRoom toEntity(){
        return StudyRoom.builder()
                .studyroomNo(studyroomNo)
                .team(team)
                .studyroomdomainaddr(studyroomdomainaddr)
                .studyroomMenteegrade(studyroomMenteegrade)
                .studyroomSubject(studyroomSubject)
                .build();
    }
}
