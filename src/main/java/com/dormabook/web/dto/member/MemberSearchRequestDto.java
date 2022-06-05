package com.dormabook.web.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchRequestDto {
    private String memberId;
    private String memberPhone;
    private String memberName;

}
