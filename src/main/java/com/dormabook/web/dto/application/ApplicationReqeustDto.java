package com.dormabook.web.dto.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationReqeustDto {
    private Long applicationNo;
    private String applicationContent;
    private Post post;
    private int applicationAcpt;
    private String applicationId;

    @Builder
    public ApplicationReqeustDto(Long applicationNo, String applicationContent, Post post, int applicationAcpt, String applicationId) {
        this.applicationNo = applicationNo;
        this.applicationContent = applicationContent;
        this.post = post;
        this.applicationAcpt = applicationAcpt;
        this.applicationId = applicationId;
    }

    public Application toEntity(){
        return Application.builder()
                .applicationNo(applicationNo)
                .applicationContent(applicationContent)
                .post(post)
                .applicationAcpt(applicationAcpt)
                .applicationId(applicationId)
                .build();
    }
}
