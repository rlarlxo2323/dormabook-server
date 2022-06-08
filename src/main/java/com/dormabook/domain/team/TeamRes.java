package com.dormabook.domain.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamRes {
    private Long teamNo;
    private String teamBooktitle;
    private String teamMenteeid;
    private String teamMentorid;
    private String teamSubject;
}
