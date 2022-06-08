package com.dormabook.web.dto.application;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {
    private String applicationContent;
    private String applicationId; //보낸사람 아이디
    private Long postNo;
    private Long applicationNo;
    private Timestamp noticeCreateAt;

    public ApplicationRequestDto(String applicationContent, String applicationId, Long postNo, Long applicationNo, Timestamp noticeCreateAt) {
        this.applicationContent = applicationContent;
        this.applicationId = applicationId;
        this.postNo = postNo;
        this.applicationNo = applicationNo;
        this.noticeCreateAt = noticeCreateAt;
    }
}
