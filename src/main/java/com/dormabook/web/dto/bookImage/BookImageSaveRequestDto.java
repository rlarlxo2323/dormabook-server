package com.dormabook.web.dto.bookImage;

import com.dormabook.domain.bookImage.BookImage;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookImageSaveRequestDto {
    private Post post;
    private String bookimageName;
    private String bookimageRoute;
    private String bookSaveimagename;

    @Builder
    public BookImageSaveRequestDto(Post post, String bookimageName, String bookimageRoute, String bookSaveimagename) {
        this.post = post;
        this.bookimageName = bookimageName;
        this.bookimageRoute = bookimageRoute;
        this.bookSaveimagename = bookSaveimagename;

    }

    public BookImage toEntity(){
        return BookImage.builder()
                .post(post)
                .bookimageName(bookimageName)
                .bookimageRoute(bookimageRoute)
                .bookSaveimagename(bookSaveimagename)
                .build();
    }
}
