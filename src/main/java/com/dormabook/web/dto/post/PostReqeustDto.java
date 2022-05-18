package com.dormabook.web.dto.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class PostReqeustDto {
    private Long postNo;
    private Member member;
    private String postSubject;
    private String postRole;
    private String postProveState;
    private String postTitle;
    private int postMatchState;
    private String postBookTitle;
    private Timestamp postCreatedAt;
    private String postContent;

    @Builder
    public PostReqeustDto(Long postNo, Member member, String postSubject, String postRole, String postProveState, String postTitle, int postMatchState, String postBookTitle, Timestamp postCreatedAt, String postContent) {
        this.postNo = postNo;
        this.member = member;
        this.postSubject = postSubject;
        this.postRole = postRole;
        this.postProveState = postProveState;
        this.postTitle = postTitle;
        this.postMatchState = postMatchState;
        this.postBookTitle = postBookTitle;
        this.postCreatedAt = postCreatedAt;
        this.postContent = postContent;
    }

    public Post toEntity(){
        return Post.builder()
                .postNo(postNo)
                .member(member)
                .postSubject(postSubject)
                .postRole(postRole)
                .postProveState(postProveState)
                .postTitle(postTitle)
                .postMatchState(postMatchState)
                .postBookTitle(postBookTitle)
                .postCreatedAt(postCreatedAt)
                .postContent(postContent)
                .build();
    }
}
