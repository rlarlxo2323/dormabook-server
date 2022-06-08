package com.dormabook.domain.team;

import com.dormabook.web.dto.application.AppContentRequestDto;
import com.dormabook.web.dto.studyroom.GetClassListResponse;
import com.dormabook.web.dto.team.PostTeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(nativeQuery = true,value = "select :userId as memberId, t.team_no as teamNo, team_subject as teamSubject, studyroom_no as studyroomNo, studyroomdomainaddr\n" +
            "from team as t\n" +
            "inner join study_room sr\n" +
            "    on t.team_no = sr.team_no\n" +
            "where t.team_mentorid=:userId or t.team_menteeid=:userId")
    List<GetClassListResponse> findByIdClassList(@Param("userId")String userId);

    @Query(nativeQuery = true, value = "select post_book_title as postBookTitle, post_subject as postSubject, application_id as applicationId, post_role as postRole\n" +
            "from post p\n" +
            "         inner join application a on p.post_no = a.post_no\n" +
            "where p.post_no = :postNo")
    PostTeamDto findByTeam(@Param("postNo")Long postNo);

    TeamRes findByTeamNo(Long teamNo);

}
