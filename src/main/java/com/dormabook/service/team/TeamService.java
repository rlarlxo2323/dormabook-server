package com.dormabook.service.team;

import com.dormabook.domain.team.Team;
import com.dormabook.domain.team.TeamRepository;
import com.dormabook.web.dto.team.PostTeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Long postTeam(String userId, Long postNo, Long applicationNo){
        PostTeamDto postTeamDto = teamRepository.findByTeam(postNo);

        System.out.println("[ROLE} : "+postTeamDto.getPostRole());

        Team team;
        if(postTeamDto.getPostRole().equals("멘토")){
            team = new Team(null, postTeamDto.getPostBookTitle(), postTeamDto.getApplicationId(), userId, postTeamDto.getPostSubject());
        }else{
            team = new Team(null, postTeamDto.getPostBookTitle(), userId, postTeamDto.getApplicationId(), postTeamDto.getPostSubject());
        }
        return teamRepository.save(team).getTeamNo();
    }


}
