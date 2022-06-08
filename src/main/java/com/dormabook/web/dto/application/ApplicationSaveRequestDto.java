package com.dormabook.web.dto.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ApplicationSaveRequestDto {
    private Post post;
    private String applicationContent;
    private String applicationId;


    @Builder
    public ApplicationSaveRequestDto(Post post, String applicationContent, String applicationId) {
        this.post = post;
        this.applicationContent = applicationContent;
        this.applicationId = applicationId;

    }

    public Application toEntity(){
        return Application.builder()
                .post(post)
                .applicationContent(applicationContent)
                .applicationId(applicationId)
                .build();
    }
}
