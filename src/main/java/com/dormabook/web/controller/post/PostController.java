package com.dormabook.web.controller.post;

import com.dormabook.service.post.PostService;
import com.dormabook.web.dto.post.PostByCommunityResponseDto;
import com.dormabook.web.dto.post.PostListResponseDto;
import com.dormabook.web.dto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/community/postlist")
    public List<PostByCommunityResponseDto> findByMenteePostList(@RequestParam("postRule")String postRule){

        return postService.findByPostList(postRule);
    }

    @GetMapping("/post_list/all")
    public List<PostListResponseDto> findByAllPostList(){
        return postService.findByAllPostList();
    }

//    @GetMapping("/community/mento_postlist")
//    public List<PostByCommunityResponseDto> findByMentoPostList(@RequestParam("postRule")String postRule){
//
//        return postService.findByPostList(postRule);
//    }
}
