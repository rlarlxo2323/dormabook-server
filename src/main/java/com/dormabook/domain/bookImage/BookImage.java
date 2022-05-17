package com.dormabook.domain.bookImage;

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
public class BookImage {

    // 자동 인덱스 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookimageNo;

    // 게시글 번호(인덱스)
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "postNo")
    @Column(nullable = false)
    private Post post;

    // 사진원본 파일명
    @Column(nullable = false)
    private String bookimageName;

    // 사진 파일 경로
    @Column(nullable = false)
    private String bookimageRoute;

    // 저장 파일명
    @Column(nullable = false)
    private String bookSaveimagename;

    @Builder
    public BookImage(Long bookimageNo, Post post, String bookimageName, String bookimageRoute, String bookSaveimagename) {
        this.bookimageNo = bookimageNo;
        this.post = post;
        this.bookimageName = bookimageName;
        this.bookimageRoute = bookimageRoute;
        this.bookSaveimagename = bookSaveimagename;
    }
}
