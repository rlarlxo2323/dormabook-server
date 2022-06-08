package com.dormabook.web.dto.notice;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.notice.Notice;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class NoticeResponseDto {
    private Application  application;
    private Timestamp noticecreateAt;

    public NoticeResponseDto(Notice entity) {
        this.application = entity.getApplication();
        this.noticecreateAt = entity.getNoticecreateAt();
    }
}
