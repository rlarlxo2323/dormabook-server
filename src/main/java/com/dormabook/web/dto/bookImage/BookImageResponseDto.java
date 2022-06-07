package com.dormabook.web.dto.bookImage;

import com.dormabook.domain.bookImage.BookImage;
import com.dormabook.domain.post.Post;
import lombok.Getter;

@Getter
public class BookImageResponseDto {
    private Post post;
    private String bookSaveImageName;
    private String bookImageName;
    private String bookImageRoute;

    public BookImageResponseDto(BookImage entity) {
        this.post = entity.getPost();
        this.bookSaveImageName = entity.getBookimageName();
        this.bookImageName = entity.getBookimageName();
        this.bookImageRoute = entity.getBookimageRoute();
    }
}
