package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class PostListResponseDto {
    private Long postNo;
    private String postTitle;
    private int postMatchState;
    private Timestamp postCreatedAt;
    private String postRole;

    public PostListResponseDto(Post entity) {
        this.postNo = entity.getPostNo();
        this.postTitle = entity.getPostTitle();
        this.postMatchState = entity.getPostMatchState();
        this.postCreatedAt = entity.getPostCreatedAt();
        this.postRole = entity.getPostRole();
    }
}
