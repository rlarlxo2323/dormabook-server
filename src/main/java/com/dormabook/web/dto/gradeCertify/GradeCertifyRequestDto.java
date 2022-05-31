package com.dormabook.web.dto.gradeCertify;

import com.dormabook.domain.gradeCertify.GradeCertify;
import com.dormabook.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeCertifyRequestDto {
    private Long gcNo;
    private String gcMentorId;
    private String gcGrade;
    private int gcState;
    private String gcSubject;
    private Post post;

    @Builder
    public GradeCertifyRequestDto(Long gcNo, String gcMentorId, String gcGrade, int gcState, String gcSubject, Post post) {
        this.gcNo = gcNo;
        this.gcMentorId = gcMentorId;
        this.gcGrade = gcGrade;
        this.gcState = gcState;
        this.gcSubject = gcSubject;
        this.post = post;
    }

    public GradeCertify toEntity(){
        return GradeCertify.builder()
                .gcNo(gcNo)
                .gcMentorId(gcMentorId)
                .gcGrade(gcGrade)
                .gcState(gcState)
                .gcSubject(gcSubject)
                .post(post)
                .build();
    }
}
