package com.dormabook.domain.post;

import com.dormabook.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@DynamicInsert
@NoArgsConstructor
@Component
public class Post {

    // 자동인덱스증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    // 작성자(회원)아이디
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // 과목
    @Column(nullable = false)
    private String postSubject;

    // 역할(멘토,멘티)
    @Column(nullable = false)
    private String postRole;

    // 성적증명상태 0:미증명, 1:증명
    @Column(nullable = false)
    @ColumnDefault("0")
    private String postProveState;

    // 제목
    @Column(nullable = false)
    private String postTitle;

    // 매칭상태 0:매칭X, 1:매칭
    @Column(nullable = false)
    @ColumnDefault("0")
    private int postMatchState;

    // 책제목
    @Column(nullable = false)
    private String postBookTitle;

    // 작성일자
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp postCreatedAt;

    // 글내용
    private String postContent;

    @Builder
    public Post(Long postNo, Member member, String postSubject, String postRole, String postProveState, String postTitle, int postMatchState, String postBookTitle, Timestamp postCreatedAt, String postContent) {
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
}
