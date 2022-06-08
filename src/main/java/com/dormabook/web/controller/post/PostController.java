package com.dormabook.web.controller.post;

import com.dormabook.domain.member.Member;
import com.dormabook.domain.post.Post;
import com.dormabook.domain.post.PostFileStorageProperties;
import com.dormabook.domain.team.Team;
import com.dormabook.service.MentorTranscript.MentorTranscriptService;
import com.dormabook.service.application.ApplicationService;
import com.dormabook.service.bookImage.BookImageService;
import com.dormabook.service.classroom.ClassroomService;
import com.dormabook.service.member.AuthService;
import com.dormabook.service.member.MemberService;
import com.dormabook.security.JwtTokenProvider;
import com.dormabook.service.post.PostService;
import com.dormabook.service.team.TeamService;
import com.dormabook.web.dto.application.AppContentRequestDto;
import com.dormabook.web.dto.application.GetAppContentResponse;
import com.dormabook.web.dto.application.GetApplicationResponse;
import com.dormabook.web.dto.bookImage.BookImageResponseDto;
import com.dormabook.web.dto.bookImage.BookImageSaveRequestDto;
import com.dormabook.web.dto.mentorTranscript.MentorTranscriptResponseDto;
import com.dormabook.web.dto.mentorTranscript.MentorTranscriptSaveRequestDto;
import com.dormabook.web.dto.post.*;
import com.dormabook.web.dto.studyroom.GetClassListResponse;
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

    PostService postService;

    JwtTokenProvider jwtTokenProvider;

    BookImageService bookImageService;

    MemberService memberService;

    MentorTranscriptService mentorTranscriptService;

    PostFileStorageProperties postFileStorageProperties;

    ClassroomService classroomService;

    ApplicationService applicationService;

    AuthService authService;

    TeamService teamService;

    @Autowired
    public PostController(PostService postService, JwtTokenProvider jwtTokenProvider, BookImageService bookImageService, MemberService memberService, MentorTranscriptService mentorTranscriptService, PostFileStorageProperties postFileStorageProperties, ClassroomService classroomService, ApplicationService applicationService, AuthService authService, TeamService teamService) {
        this.postService = postService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.bookImageService = bookImageService;
        this.memberService = memberService;
        this.mentorTranscriptService = mentorTranscriptService;
        this.postFileStorageProperties = postFileStorageProperties;
        this.classroomService = classroomService;
        this.applicationService = applicationService;
        this.authService = authService;
        this.teamService = teamService;
    }

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

    @PostMapping(value = "/mento_post/upload/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String mentoPostUpload(@RequestPart UploadPostRequestDto dto,
                                     @RequestPart("file") MultipartFile file,
                                  @RequestPart("file2") MultipartFile file2) {
        Optional<Member> member = memberService.getInfo(dto.getMemberId());
        PostSaveRequestDto saveDto = new PostSaveRequestDto(member.get(), dto.getPostSubject(), dto.getPostRole(), dto.getPostTitle(), dto.getPostBookTitle(), dto.getPostContent());
        PostResponseDto info = postService.save(saveDto);
        Optional<Post> post = postService.getPostNo(info.getPostNo());  //게시글 저장 후 게시글 No뽑아옥

        String bookSaveImageName = postService.mentoPostUpload(file);
        String imageName = file.getOriginalFilename();
        String imageRoute = postFileStorageProperties.getUploadDir();

        Post postNo = post.get();

        BookImageSaveRequestDto bookImageSaveRequestDto = BookImageSaveRequestDto.builder() //BookImage DB 저장
                .bookimageName(imageName)
                .bookimageRoute(imageRoute)
                .bookSaveimagename(bookSaveImageName)
                .post(postNo)
                .build();

        BookImageResponseDto responseDto = bookImageService.save(bookImageSaveRequestDto); //BookImage DB 저장


        String mentoSaveImageName = postService.mentoPostUpload(file2);
        String mentoImageName = file.getOriginalFilename();
        String mentoImageRoute = postFileStorageProperties.getUploadDir();

        MentorTranscriptSaveRequestDto mentorTranscriptSaveRequestDto = MentorTranscriptSaveRequestDto.builder()
                .mentoImageName(mentoImageName)
                .mentoImageRoute(mentoImageRoute)
                .mentoSaveImageName(mentoSaveImageName)
                .post(postNo)
                .build();

        MentorTranscriptResponseDto responseDto2 = mentorTranscriptService.save(mentorTranscriptSaveRequestDto);

        String test = responseDto.getBookImageRoute();
        String test2 = responseDto2.getMentoSaveImageName();
        return test+" + 경로에 이미지 저장됨 \n" +test2 + "경로에 성적증명서 저장됨";
    }

    @GetMapping("/mypage/postlist")
    public List<PostListResponseDto> findByPostList(HttpServletRequest request){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return postService.findByIdPostList(userId);
    }

    @GetMapping("/mypage/classlist")
    public List<GetClassListResponse> findByClassList(HttpServletRequest request){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return classroomService.findByIdClassList(userId);
    }

    @GetMapping("/mypage/applicationlist")
    public List<GetApplicationResponse> findByApplicationList(HttpServletRequest request){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        return applicationService.findByIdApplicationList(userId);
    }

    @PostMapping("/mypage/modifyintro")
    public int modifyByIntro(HttpServletRequest request, @RequestBody RequestIntroDto requestIntroDto){

        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);

        String userId = postService.findByIdJwt(jwtToken);

        String userIntroduce = requestIntroDto.getMemberIntroduce();
        return authService.modifyByIntro(userId, userIntroduce);
    }

    @GetMapping("/mypage/application")
    public GetAppContentResponse getByAppContent(@RequestParam("applicationNo")String applicationNo){
        return applicationService.getByAppContent(applicationNo);
    }

    @PostMapping("/mypage/modifyapplication")
    public int modifyByAppContent(HttpServletRequest request, @RequestBody AppContentRequestDto appContentRequestDto){
        System.out.println("1");
        String jwt = jwtTokenProvider.resolveToken(request);
        val jwtToken = jwt.substring(7);
        int result = applicationService.modifyByAppContent(appContentRequestDto.getApplicationNo(), appContentRequestDto.getFlag());
        String userId = postService.findByIdJwt(jwtToken);
        System.out.println("2");
        if (result == 1){
            Long teamNo = teamService.postTeam(userId, appContentRequestDto.getPostNo(), appContentRequestDto.getApplicationNo());
            String subject = classroomService.getSubject(teamNo);
            classroomService.postStudyRoom(subject, appContentRequestDto.getAddr(), teamNo);
        }

        return result;
    }
}
