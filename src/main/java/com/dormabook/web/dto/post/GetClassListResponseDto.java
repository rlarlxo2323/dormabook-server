package com.dormabook.web.dto.post;

import com.dormabook.domain.studyroom.StudyRoom;
import com.dormabook.domain.team.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetClassListResponseDto {
    private String memberId;
    private Long teamNo;
    private String teamSubject;
    private Long studyroomNo;
    private String studyroomdomainaddr;

    public GetClassListResponseDto(Team entity, StudyRoom entitity, String memberId){
        this.memberId = memberId;
        this.teamNo = entity.getTeamNo();
        this.teamSubject = entity.getTeamSubject();
        this.studyroomNo = entitity.getStudyroomNo();
        this.studyroomdomainaddr = entitity.getStudyroomdomainaddr();
    }
}
