package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostByCommunityResponseDto {
    private String postTitle;
    private int postMatchState;

    public PostByCommunityResponseDto(Post entity) {
        this.postTitle = entity.getPostTitle();
        this.postMatchState = entity.getPostMatchState();

    }
}
