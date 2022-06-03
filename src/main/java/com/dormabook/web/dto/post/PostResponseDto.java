package com.dormabook.web.dto.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class PostResponseDto {
    private Long postNo;
    private Member member;
    private String postSubject;
    private String postRule;
    private String postProveState;
    private String postTitle;
    private int postMatchState;
    private String postBookTitle;
    private Timestamp postCreatedAt;
    private String postContent;

    public PostResponseDto(Post entity) {
        this.postNo = entity.getPostNo();
        this.member = entity.getMember();
        this.postSubject = entity.getPostSubject();
        this.postRule = entity.getPostRole();
        this.postProveState = entity.getPostProveState();
        this.postTitle = entity.getPostTitle();
        this.postMatchState = entity.getPostMatchState();
        this.postBookTitle = entity.getPostBookTitle();
        this.postCreatedAt = entity.getPostCreatedAt();
        this.postContent = entity.getPostContent();
    }
}
