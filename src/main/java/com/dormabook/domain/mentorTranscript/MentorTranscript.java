package com.dormabook.domain.mentorTranscript;

import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class MentorTranscript {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentortsNo;

    // 게시글 번호(인덱스)
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "postNo")
    private Post post;

    // 사진원본 파일명
    @Column(nullable = false)
    private String mentortsImagename;

    // 사진 파일 경로
    @Column(nullable = false)
    private String mentortsImageroute;

    // 저장 파일명
    @Column(nullable = false)
    private String mentortsSaveimagename;

    @Builder
    public MentorTranscript(Long mentortsNo, Post post, String mentortsImagename, String mentortsImageroute, String mentortsSaveimagename) {
        this.mentortsNo = mentortsNo;
        this.post = post;
        this.mentortsImagename = mentortsImagename;
        this.mentortsImageroute = mentortsImageroute;
        this.mentortsSaveimagename = mentortsSaveimagename;
    }
}
