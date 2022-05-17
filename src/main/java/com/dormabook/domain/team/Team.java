package com.dormabook.domain.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class Team {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamNo;

    // 멘토 아이디
    @Column(nullable = false)
    private String teamMentorid;

    // 멘티 아이디
    @Column(nullable = false)
    private String teamMenteeid;

    // 책 제목
    @Column(nullable = false)
    private String teamBooktitle;

    // 과목
    @Column(nullable = false)
    private String teamSubject;

    @Builder
    public Team(Long teamNo, String teamMentorid, String teamMenteeid, String teamBooktitle, String teamSubject) {
        this.teamNo = teamNo;
        this.teamMentorid = teamMentorid;
        this.teamMenteeid = teamMenteeid;
        this.teamBooktitle = teamBooktitle;
        this.teamSubject = teamSubject;
    }
}
