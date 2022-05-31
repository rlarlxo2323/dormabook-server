package com.dormabook.web.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequestDto {

    private String memberId;
    private String memberPwd;
}
