package com.dormabook.service.classroom;

import com.dormabook.domain.studyroom.StudyRoom;
import com.dormabook.domain.studyroom.StudyRoomRepository;
import com.dormabook.domain.team.Team;
import com.dormabook.domain.team.TeamRepository;
import com.dormabook.domain.team.TeamRes;
import com.dormabook.web.dto.studyroom.GetClassListResponse;
import com.dormabook.web.dto.team.PostTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private final StudyRoomRepository studyRoomRepository;

    public ClassroomService(TeamRepository teamRepository, StudyRoomRepository studyRoomRepository) {
        this.teamRepository = teamRepository;
        this.studyRoomRepository = studyRoomRepository;
    }

    public List<GetClassListResponse> findByIdClassList(String userId){

        return teamRepository.findByIdClassList(userId);
    }

    public String getSubject(Long teamNo){
        return teamRepository.findByTeamNo(teamNo).getTeamSubject();
    }

    public void postStudyRoom(String subject, String addr, Long teamNo){
        TeamRes teamRes = teamRepository.findByTeamNo(teamNo);
        Team team = new Team(teamRes.getTeamNo(), teamRes.getTeamBooktitle(), teamRes.getTeamMenteeid(), teamRes.getTeamMentorid(), teamRes.getTeamSubject());
        StudyRoom studyRoom = new StudyRoom(null, team, addr, null, subject);
        studyRoomRepository.save(studyRoom);
    }
}
