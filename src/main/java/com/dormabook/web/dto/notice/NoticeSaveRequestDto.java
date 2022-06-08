package com.dormabook.web.dto.notice;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    private Application application;
    private Timestamp noticecreateAt;

    @Builder

    public NoticeSaveRequestDto(Application application, Timestamp noticecreateAt) {
        this.application = application;
        this.noticecreateAt = noticecreateAt;
    }

    public Notice toEntity(){
        return Notice.builder()
                .application(application)
                .noticecreateAt(noticecreateAt)
                .build();
    }
}
