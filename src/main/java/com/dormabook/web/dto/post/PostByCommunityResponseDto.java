package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostByCommunityResponseDto {
    private Long postNo;
    private String postTitle;
    private int postMatchState;

    public PostByCommunityResponseDto(Post entity) {
        this.postNo = entity.getPostNo();
        this.postTitle = entity.getPostTitle();
        this.postMatchState = entity.getPostMatchState();

    }
}
