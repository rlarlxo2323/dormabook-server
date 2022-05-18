package com.dormabook.web.dto.notice;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class NoticeRequestDto {
    private Long noticeNo;
    private Application application;
    private Timestamp noticecreateAt;

    @Builder
    public NoticeRequestDto(Long noticeNo, Application application, Timestamp noticecreateAt) {
        this.noticeNo = noticeNo;
        this.application = application;
        this.noticecreateAt = noticecreateAt;
    }

    public Notice toEntity(){
        return Notice.builder()
                .noticeNo(noticeNo)
                .application(application)
                .noticecreateAt(noticecreateAt)
                .build();
    }
}
