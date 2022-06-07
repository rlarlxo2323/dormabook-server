package com.dormabook.web.dto.mentorTranscript;

import com.dormabook.domain.mentorTranscript.MentorTranscript;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorTranscriptSaveRequestDto {
    private Post post;
    private String mentoImageName;
    private String mentoImageRoute;
    private String mentoSaveImageName;

    @Builder

    public MentorTranscriptSaveRequestDto(Post post, String mentoImageName, String mentoImageRoute, String mentoSaveImageName) {
        this.post = post;
        this.mentoImageName = mentoImageName;
        this.mentoImageRoute = mentoImageRoute;
        this.mentoSaveImageName = mentoSaveImageName;
    }

    public MentorTranscript toEntity(){
        return MentorTranscript.builder()
                .post(post)
                .mentortsImagename(mentoImageName)
                .mentortsImageroute(mentoImageRoute)
                .mentortsSaveimagename(mentoSaveImageName)
                .build();
    }
}
