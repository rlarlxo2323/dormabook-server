package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommunityRequestDto {
    private String postRole;

    @Builder
    public CommunityRequestDto(String postRole) {
        this.postRole = postRole;
    }

    public Post toEntity(){
        return Post.builder()
                .postRole(postRole).build();
    }
}
