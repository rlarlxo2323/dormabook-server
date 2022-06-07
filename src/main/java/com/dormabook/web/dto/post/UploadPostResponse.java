package com.dormabook.web.dto.post;

import com.dormabook.domain.post.Post;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UploadPostResponse {
    private String postTitle;
    private String postSubject;
    private String postBookTitle;
    private String postContent;
    private String memberId;
    private Long postNo;

    public UploadPostResponse(Post entity) {
        this.postTitle = entity.getPostTitle();
        this.postSubject = entity.getPostSubject();
        this.postBookTitle = entity.getPostBookTitle();
        this.postContent = entity.getPostContent();
        this.memberId = entity.getPostBookTitle();
        this.postNo = entity.getPostNo();
    }


}
