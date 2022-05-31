package com.dormabook.domain.gradeCertify;

import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Component
public class GradeCertify {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcNo;

    // 멘토(회원) 아이디
    @Column(nullable = false)
    private String gcMentorId;

    // 성적
    @Column(nullable = false)
    private String gcGrade;

    // 인증처리상태 N:미인증, Y:인증
    @Column(nullable = false)
    @ColumnDefault("0")
    private int gcState;

    // 과목
    @Column(nullable = false)
    private String gcSubject;

    // 게시글 번호(인덱스)
    @OneToOne(targetEntity = Post.class)
    @JoinColumn(name = "postNo", nullable = false)
    private Post post;

    @Builder
    public GradeCertify(Long gcNo, String gcMentorId, String gcGrade, int gcState, String gcSubject, Post post) {
        this.gcNo = gcNo;
        this.gcMentorId = gcMentorId;
        this.gcGrade = gcGrade;
        this.gcState = gcState;
        this.gcSubject = gcSubject;
        this.post = post;
    }
}
