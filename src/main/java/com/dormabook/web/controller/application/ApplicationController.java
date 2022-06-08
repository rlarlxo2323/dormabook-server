package com.dormabook.web.controller.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.post.Post;
import com.dormabook.service.application.ApplicationService;
import com.dormabook.service.notice.NoticeService;
import com.dormabook.service.post.PostService;
import com.dormabook.web.dto.application.ApplicationRequestDto;
import com.dormabook.web.dto.application.ApplicationResponseDto;
import com.dormabook.web.dto.application.ApplicationSaveRequestDto;
import com.dormabook.web.dto.notice.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    PostService postService;

    @Autowired
    NoticeService noticeService;

    //멘토,멘티 신청서 작성
    @PostMapping(value = "/applicationform") //@RequestPart(value = "data")
    public void mentoAndMenteeApplication(@RequestBody ApplicationRequestDto dto){
        Optional <Post> post = postService.getPostNo(dto.getPostNo());
        ApplicationSaveRequestDto saveRequestDto = new ApplicationSaveRequestDto(post.get(), dto.getApplicationContent(), dto.getApplicationId());
        ApplicationResponseDto info = applicationService.save(saveRequestDto);
        Optional<Application> application = applicationService.getApplicationNo(info.getApplicationNo());
        Application applicationNo = application.get(); // applicationNo 추출해서 사용 Notice 작성을 위함
        Timestamp a = new Timestamp(System.currentTimeMillis()); //현재 시간 추가

        NoticeSaveRequestDto noticeSaveRequestDto = NoticeSaveRequestDto.builder()
                .application(applicationNo)
                .noticecreateAt(a)
                .build();

        noticeService.save(noticeSaveRequestDto); //DB에 notice 저장
    }

}
