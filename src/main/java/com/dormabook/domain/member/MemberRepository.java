package com.dormabook.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

    //아이디로 조회 (반환형 - 객체)
    @Query(nativeQuery = true, value = "SELECT * FROM member WHERE member_id =:id")
    Member findByMemberId(@Param("id")String memberId);

    @Query(nativeQuery = true, value = "SELECT member_id FROM member WHERE member_name =:name and member_phone =:phone")
    String findIdByName(@Param("name")String memberName, @Param("phone")String memberPhone);

    @Query(nativeQuery = true, value = "SELECT member_id FROM member WHERE member_name =:name and member_phone =:phone and member_id =:id")
    String findPwById(@Param("name")String memberName, @Param("phone")String memberPhone, @Param("id")String memberId);

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE member SET member_pwd =:pwd WHERE member_id =:id")
    int modifyPwById(@Param("id")String memberId, @Param("pwd")String memberPwd);

    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE member SET member_introduce =:introduce WHERE member_id =:userId")
    int modifyByIntro(@Param("userId")String memberId, @Param("introduce")String memberIntroduce);
}
