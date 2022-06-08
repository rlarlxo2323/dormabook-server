package com.dormabook.web.dto.post;


import java.sql.Timestamp;
public interface GetPostClassResponse {
    String getMemberName();
    String getPostTitle();
    String getPostContent();
    Timestamp getPostCreatedAt();
    int getPostMatchState();
    String getBookSaveimagename();
    String getBookimageRoute();
}
