package com.dormabook.web.dto.member;

import com.dormabook.domain.member.Member;
import com.dormabook.model.Role;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberStudentId;
    private String memberPhone;
    private String memberMajor;
    private String memberCollege;
    private String memberIntroduce;
    private int memberPoint;
    private Role memberPermission;

    public MemberResponseDto(Member entity){
        this.memberId = entity.getMemberId();
        this.memberPwd = entity.getMemberPwd();
        this.memberName = entity.getMemberName();
        this.memberStudentId = entity.getMemberStudentId();
        this.memberPhone = entity.getMemberPhone();
        this.memberMajor = entity.getMemberMajor();
        this.memberCollege = entity.getMemberCollege();
        this.memberIntroduce = entity.getMemberIntroduce();
        this.memberPoint = entity.getMemberPoint();
        this.memberPermission = entity.getMemberPermission();
    }
}
