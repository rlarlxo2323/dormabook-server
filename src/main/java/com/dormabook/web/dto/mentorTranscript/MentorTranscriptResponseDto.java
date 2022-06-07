package com.dormabook.web.dto.mentorTranscript;

import com.dormabook.domain.mentorTranscript.MentorTranscript;
import com.dormabook.domain.post.Post;
import lombok.Getter;

@Getter
public class MentorTranscriptResponseDto {
    private Post post;
    private String mentoImageName;
    private String mentoImageRoute;
    private String mentoSaveImageName;

    public MentorTranscriptResponseDto(MentorTranscript entity) {
        this.post = entity.getPost();
        this.mentoImageName = entity.getMentortsImagename();
        this.mentoImageRoute = entity.getMentortsImageroute();
        this.mentoSaveImageName = entity.getMentortsImagename();
    }
}
