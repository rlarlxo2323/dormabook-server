package com.dormabook.domain.member;

import com.dormabook.domain.post.Post;
import com.dormabook.model.Role;
//import com.dormabook.web.dto.member.MemberRequestDto;
import com.dormabook.web.dto.member.MemberSignupRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Member {

    // 회원 아이디
    @Id
    private String memberId;

    // 회원 패스워드
    @Column(nullable = false)
    private String memberPwd;

    // 회원 이름
    @Column(nullable = false)
    private String memberName;

    // 회원 학번
    @Column(nullable = false)
    private String memberStudentId;

    // 회원 연락처
    @Column(nullable = false)
    private String memberPhone;

    // 학과
    @Column(nullable = false)
    private String memberMajor;

    // 단과대학
    @Column(nullable = false)
    private String memberCollege;

    // 자기소개
    private String memberIntroduce;

    // 포인트
    @Column(nullable = false)
    @ColumnDefault("0")
    private int memberPoint;

    // 0 : 일반유저, 1: 관리자
    @Enumerated(EnumType.STRING)
    private Role memberPermission;

//    @Builder
//    public Member(String memberId, String memberPwd, String memberName, String memberStudentId, String memberPhone, String memberMajor, String memberCollege, String memberIntroduce, int memberPoint) {
//        this.memberId = memberId;
//        this.memberPwd = memberPwd;
//        this.memberName = memberName;
//        this.memberStudentId = memberStudentId;
//        this.memberPhone = memberPhone;
//        this.memberMajor = memberMajor;
//        this.memberCollege = memberCollege;
//        this.memberIntroduce = memberIntroduce;
//        this.memberPoint = memberPoint;
//        this.memberPermission = Role.USER;
//    }

    public Member(MemberSignupRequestDto request) {
        memberId = request.getMemberId();
        memberPwd = request.getMemberPwd();
        memberName = request.getMemberName();
        memberStudentId = request.getMemberStudentId();
        memberPhone = request.getMemberPhone();
        memberMajor = request.getMemberMajor();
        memberCollege = request.getMemberCollege();
        memberIntroduce = request.getMemberIntroduce();
        memberPermission = Role.USER;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder){
        memberPwd = passwordEncoder.encode(memberPwd);
    }
}