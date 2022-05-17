package com.dormabook.domain.studyRoom;

import com.dormabook.domain.post.Post;
import com.dormabook.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class StudyRoom {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyroomNo;

    // 팀 번호(인덱스)
    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "teamNo")
    private Team team;

    // 도메인 주소
    @Column(nullable = false)
    private String studyroomdomainaddr;

    // 멘티성적

    private String studyroomMenteegrade;

    // 과목
    @Column(nullable = false)
    private String studyroomSubject;

    @Builder
    public StudyRoom(Long studyroomNo, Team team, String studyroomdomainaddr, String studyroomMenteegrade, String studyroomSubject) {
        this.studyroomNo = studyroomNo;
        this.team = team;
        this.studyroomdomainaddr = studyroomdomainaddr;
        this.studyroomMenteegrade = studyroomMenteegrade;
        this.studyroomSubject = studyroomSubject;
    }
}
