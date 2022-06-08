package com.dormabook.web.dto.application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppContentRequestDto {
    private Long applicationNo;
    private int flag;
    private Long postNo;

    private String addr;

    private String postRole;

    @Builder
    public AppContentRequestDto(Long applicationNo, int flag, Long postNo, String addr, String postRole){
        this.applicationNo = applicationNo;
        this.flag = flag;
        this.postNo = postNo;
        this.addr = addr;
        this.postRole = postRole;
    }
}
