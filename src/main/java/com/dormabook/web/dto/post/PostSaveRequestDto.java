package com.dormabook.web.dto.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private Member member;
    private String postSubject;
    private String postRole;
    private String postTitle;
    private String postBookTitle;
    private String postContent;

    @Builder
    public PostSaveRequestDto(Member member, String postSubject, String postRole, String postTitle, String postBookTitle, String postContent) {
        this.member = member;
        this.postSubject = postSubject;
        this.postRole = postRole;
        this.postTitle = postTitle;
        this.postBookTitle = postBookTitle;
        this.postContent = postContent;
    }

    public Post toEntity(){
        return Post.builder()
                .member(member)
                .postSubject(postSubject)
                .postRole(postRole)
                .postTitle(postTitle)
                .postBookTitle(postBookTitle)
                .postContent(postContent)
                .build();
    }
}
