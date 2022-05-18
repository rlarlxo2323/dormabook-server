package com.dormabook.web.dto.team;

import com.dormabook.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRequestDto {
    private Long teamNo;
    private String teamMentorid;
    private String teamMenteeid;
    private String teamBooktitle;
    private String teamSubject;

    @Builder

    public TeamRequestDto(Long teamNo, String teamMentorid, String teamMenteeid, String teamBooktitle, String teamSubject) {
        this.teamNo = teamNo;
        this.teamMentorid = teamMentorid;
        this.teamMenteeid = teamMenteeid;
        this.teamBooktitle = teamBooktitle;
        this.teamSubject = teamSubject;
    }

    public Team toEntity(){
        return Team.builder()
                .teamNo(teamNo)
                .teamMentorid(teamMentorid)
                .teamMenteeid(teamMenteeid)
                .teamBooktitle(teamBooktitle)
                .teamSubject(teamSubject)
                .build();
    }
}
