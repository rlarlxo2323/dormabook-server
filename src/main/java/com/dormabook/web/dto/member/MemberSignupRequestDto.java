package com.dormabook.web.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequestDto {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberStudentId;
    private String memberPhone;
    private String memberMajor;
    private String memberCollege;
    private String memberIntroduce;
}
