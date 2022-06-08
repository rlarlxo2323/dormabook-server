package com.dormabook.domain.application;

import com.dormabook.web.dto.application.GetAppContentResponse;
import com.dormabook.web.dto.application.GetApplicationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query(nativeQuery = true,value = "select p.post_no as postNo, post_role as postRule, post_title as postTitle, noticecreate_at as NoticecreateAt, application_no as applicationNo\n" +
            "from post p inner join\n" +
            "(select a.application_no, post_no, noticecreate_at, application_acpt\n" +
            "from application a inner join notice n on a.application_no = n.application_no) arr\n" +
            "on p.post_no = arr.post_no\n" +
            "where member_id=:userId and application_acpt=0;")
    List<GetApplicationResponse> findByIdApplicationList(@Param("userId")String userId);

    @Query(nativeQuery = true,value = "select member_name as memberName, member_id as memberId, member_college as memberCollege, member_major as memberMajor, application_content as applicationContent\n" +
            "from application a inner join member n on a.application_id = n.member_id\n" +
            "where application_no=:applicationNo")
    GetAppContentResponse getByAppContent(@Param("applicationNo")String applicationNo);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE application SET application_acpt =:flag WHERE application_no =:applicationNo")
    int modifyByAppContent(@Param("applicationNo")Long applicationNo, @Param("flag")int flag);
}
