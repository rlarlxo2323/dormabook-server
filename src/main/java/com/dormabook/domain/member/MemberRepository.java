package com.dormabook.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {

    //아이디로 조회 (반환형 - 객체)
    @Query(nativeQuery = true, value = "SELECT * FROM member WHERE member_id =:id")
    Member findByMemberid(@Param("id")String memberId);
}
