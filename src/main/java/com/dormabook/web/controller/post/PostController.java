package com.dormabook.web.controller.post;

import com.dormabook.security.JwtTokenProvider;
import com.dormabook.service.post.PostService;
import com.dormabook.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/post")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/community/postlist")
    public List<PostByCommunityResponseDto> findByMenteePostList(@RequestParam("postRule")String postRule){

        return postService.findByPostList(postRule);
    }

    @GetMapping("/community/mypage/postlist")
    public List<PostListResponseDto> findByPostList(HttpServletRequest request){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.findByIdPostList(userId);
    }

    @GetMapping("/community/mypage/classlist")
    public List<GetClassListResponse> findByClassList(HttpServletRequest request){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.findByIdClassList(userId);
    }
}
