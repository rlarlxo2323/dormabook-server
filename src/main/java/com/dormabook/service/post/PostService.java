package com.dormabook.service.post;

import com.dormabook.domain.post.Post;
import com.dormabook.domain.post.PostRepository;
import com.dormabook.domain.team.TeamRepository;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final TeamRepository teamRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public List<PostByCommunityResponseDto> findByPostList(String postRule){
        return postRepository.findByPostList(postRule)
                .stream()
                .map(PostByCommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostListResponseDto> findByIdPostList(String userId){

        return postRepository.findByIdPostList(userId)
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<GetClassListResponse> findByIdClassList(String userId){

        return teamRepository.findByIdClassList(userId);
    }

    public String findByIdJwt(String jwt){
        return jwtTokenProvider.getAuthentication(jwt).getName();
    }
}
