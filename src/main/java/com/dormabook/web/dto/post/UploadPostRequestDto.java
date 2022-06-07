package com.dormabook.web.dto.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UploadPostRequestDto {
    private String postTitle;
    private String postSubject;
    private String postRole;
    private String postBookTitle;
    private String postContent;
    private String memberId;

    public UploadPostRequestDto(String postTitle, String postSubject, String postRole, String postBookTitle, String postContent, String memberId) {
        this.postTitle = postTitle;
        this.postSubject = postSubject;
        this.postRole = postRole;
        this.postBookTitle = postBookTitle;
        this.postContent = postContent;
        this.memberId = memberId;
    }
}
