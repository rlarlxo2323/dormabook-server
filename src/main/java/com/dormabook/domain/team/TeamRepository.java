package com.dormabook.domain.team;

import com.dormabook.web.dto.post.GetClassListResponse;
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
}
