package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class PostListResponseDto {

    private String postTitle;
    private int postMatchState;
    private Timestamp postCreatedAt;

    public PostListResponseDto(Post entity) {
        this.postTitle = entity.getPostTitle();
        this.postMatchState = entity.getPostMatchState();
        this.postCreatedAt = entity.getPostCreatedAt();
    }
}
