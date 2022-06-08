package com.dormabook.web.controller.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import com.dormabook.domain.post.PostFileStorageProperties;
import com.dormabook.service.MentorTranscript.MentorTranscriptService;
import com.dormabook.service.bookImage.BookImageService;
import com.dormabook.service.member.MemberService;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.service.post.PostService;
import com.dormabook.web.dto.bookImage.BookImageSaveRequestDto;
import com.dormabook.web.dto.mentorTranscript.MentorTranscriptSaveRequestDto;
import com.dormabook.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    BookImageService bookImageService;

    @Autowired
    MemberService memberService;

    @Autowired
    MentorTranscriptService mentorTranscriptService;

    @Autowired
    PostFileStorageProperties postFileStorageProperties;

    @GetMapping("/community/postlist")
    public List<PostByCommunityResponseDto> findByMenteePostList(@RequestParam("postRule") String postRule) {

        return postService.findByPostList(postRule);
    }

    //전체 게시글 조회
    @GetMapping("/post_list/all")
    public List<PostListResponseDto> findByAllPostList() {
        return postService.findByAllPostList();
    }

    //멘토 멘티 게시글만 역할에 따라 조회
    @GetMapping("/post_list")
    public List<PostListResponseDto> findByRolePostList(@RequestParam("postRule") String postRule) {

        return postService.findByRolePostList(postRule);
    }

    //멘티 게시글 단건 조회 postNo로 조회함.
    @GetMapping("/mentee_post")
    public Post findByMenteePost(@RequestParam("postNo") Long postNo) {
        Post p = postService.findByMenteePost(postNo);
        return p;
    }
    @GetMapping("/mento_post")
    public GetPostClassResponse findByPostClass(@RequestParam("postNo")Long postNo){
        return postService.findByPostClass(postNo);
    }

    //멘티 게시글 작성
    @PostMapping(value = "/mentee_post/upload/post")
    public void menteePostUpload(@RequestPart("data") UploadPostRequestDto dto) {
        Optional<Member> member = memberService.getInfo(dto.getMemberId());
        PostSaveRequestDto saveDto = new PostSaveRequestDto(member.get(), dto.getPostSubject(), dto.getPostRole(), dto.getPostTitle(), dto.getPostBookTitle(), dto.getPostContent());
        postService.save(saveDto);

    }

    //멘토 게시글 작성
    @PostMapping(value = "/mento_post/upload/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String mentoPostUpload(@RequestPart(value = "data") UploadPostRequestDto dto,
                                  @RequestPart(value = "file", required = false) MultipartFile file,
                                  @RequestPart(value = "file2") MultipartFile file2) {
        Optional<Member> member = memberService.getInfo(dto.getMemberId());
        PostSaveRequestDto saveDto = new PostSaveRequestDto(member.get(), dto.getPostSubject(), dto.getPostRole(), dto.getPostTitle(), dto.getPostBookTitle(), dto.getPostContent());
        PostResponseDto info = postService.save(saveDto);
        Optional<Post> post = postService.getPostNo(info.getPostNo());  //게시글 저장 후 게시글No 뽑아오기
        Post postNo = post.get();

        if (file != null) {
            String bookSaveImageName = postService.mentoPostUpload(file);
            String imageName = file.getOriginalFilename();
            String imageRoute = postFileStorageProperties.getUploadDir();

            BookImageSaveRequestDto bookImageSaveRequestDto = BookImageSaveRequestDto.builder() //BookImage DB 저장
                    .bookimageName(imageName)
                    .bookimageRoute(imageRoute)
                    .bookSaveimagename(bookSaveImageName)
                    .post(postNo)
                    .build();

            bookImageService.save(bookImageSaveRequestDto); //BookImage DB 저장

        }
        if (file2 != null) {
            String mentoSaveImageName = postService.mentoPostUpload(file2);
            String mentoImageName = file2.getOriginalFilename();
            String mentoImageRoute = postFileStorageProperties.getUploadDir2();

            MentorTranscriptSaveRequestDto mentorTranscriptSaveRequestDto = MentorTranscriptSaveRequestDto.builder()
                    .mentoImageName(mentoImageName)
                    .mentoImageRoute(mentoImageRoute)
                    .mentoSaveImageName(mentoSaveImageName)
                    .post(postNo)
                    .build();

            mentorTranscriptService.save(mentorTranscriptSaveRequestDto);
            String ok = "게시글 작성 완료";
            return ok;
        } else {
            String ok = "게시글 작성 오류";
            return ok;
        }

    }


    @GetMapping("/community/mypage/postlist")
    public List<PostListResponseDto> findByPostList(HttpServletRequest request) {

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.findByIdPostList(userId);
    }

    @GetMapping("/community/mypage/classlist")
    public List<GetClassListResponse> findByClassList(HttpServletRequest request) {

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.findByIdClassList(userId);
    }

    @GetMapping("/community/profile")
    public List<CommunityProfileResDto> getProfile(HttpServletRequest request) {
        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.getProfile(userId);
    }
}
