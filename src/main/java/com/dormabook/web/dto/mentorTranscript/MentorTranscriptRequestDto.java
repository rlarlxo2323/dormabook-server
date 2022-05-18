package com.dormabook.web.dto.mentorTranscript;

import com.dormabook.domain.mentorTranscript.MentorTranscript;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MentorTranscriptRequestDto {
    private Long mentortsNo;
    private Post post;
    private String mentortsImagename;
    private String mentortsImageroute;
    private String mentortsSaveimagename;

    @Builder
    public MentorTranscriptRequestDto(Long mentortsNo, Post post, String mentortsImagename, String mentortsImageroute, String mentortsSaveimagename) {
        this.mentortsNo = mentortsNo;
        this.post = post;
        this.mentortsImagename = mentortsImagename;
        this.mentortsImageroute = mentortsImageroute;
        this.mentortsSaveimagename = mentortsSaveimagename;
    }

    public MentorTranscript toEntity(){
        return MentorTranscript.builder()
                .mentortsNo(mentortsNo)
                .post(post)
                .mentortsImagename(mentortsImagename)
                .mentortsImageroute(mentortsImageroute)
                .mentortsSaveimagename(mentortsSaveimagename)
                .build();
    }
}
