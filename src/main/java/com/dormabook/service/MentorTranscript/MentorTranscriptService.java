package com.dormabook.service.MentorTranscript;

import com.dormabook.domain.mentorTranscript.MentorTranscriptRepository;
import com.dormabook.web.dto.mentorTranscript.MentorTranscriptResponseDto;
import com.dormabook.web.dto.mentorTranscript.MentorTranscriptSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MentorTranscriptService {

    private final MentorTranscriptRepository mentorTranscriptRepository;

    public MentorTranscriptResponseDto save(MentorTranscriptSaveRequestDto dto){
        return new MentorTranscriptResponseDto(mentorTranscriptRepository.save(dto.toEntity()));
    }
}
