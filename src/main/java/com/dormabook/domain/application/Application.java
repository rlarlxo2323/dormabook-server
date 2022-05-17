package com.dormabook.domain.application;

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
public class Application {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    // 신청서 작성 내용
    @Column(nullable = false)
    private String applicationContent;

    // 게시글 번호(인덱스)
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "postNo")
    @Column(nullable = false)
    private Post post;

    // 신청서 수락 여부 0:미수락, 1:수락
    @Column(nullable = false)
    @ColumnDefault("0")
    private int applicationAcpt;

    // 신청자 아이디
    @Column(nullable = false)
    private String applicationId;

    @Builder
    public Application(Long applicationNo, String applicationContent, Post post, int applicationAcpt, String applicationId) {
        this.applicationNo = applicationNo;
        this.applicationContent = applicationContent;
        this.post = post;
        this.applicationAcpt = applicationAcpt;
        this.applicationId = applicationId;
    }
}
