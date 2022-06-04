package com.dormabook.service.post;

import com.dormabook.domain.post.Post;
import com.dormabook.domain.post.PostRepository;
import com.dormabook.web.dto.post.PostByCommunityResponseDto;
import com.dormabook.web.dto.post.PostListResponseDto;
import com.dormabook.web.dto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<PostByCommunityResponseDto> findByPostList(String postRule){
        return postRepository.findByPostList(postRule)
                .stream()
                .map(PostByCommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostListResponseDto> findByAllPostList(){
        return postRepository.findByAllPostList()
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
