package com.dormabook.web.dto.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.post.Post;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ApplicationResponseDto {
    private Long applicationNo;
    private Post post;
    private String applicationContent;
    private String applicationId;


    public ApplicationResponseDto(Application entity) {
        this.applicationNo = entity.getApplicationNo();
        this.post = entity.getPost();
        this.applicationContent = entity.getApplicationContent();
        this.applicationId = entity.getApplicationId();

    }
}
