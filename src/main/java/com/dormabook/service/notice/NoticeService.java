package com.dormabook.service.notice;

import com.dormabook.domain.notice.NoticeRepository;
import com.dormabook.web.dto.notice.NoticeResponseDto;
import com.dormabook.web.dto.notice.NoticeSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeResponseDto save(NoticeSaveRequestDto dto){
        return new NoticeResponseDto(noticeRepository.save(dto.toEntity()));
    }

}
