package com.dormabook.service.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.application.ApplicationRepository;
import com.dormabook.web.dto.application.ApplicationResponseDto;
import com.dormabook.web.dto.application.ApplicationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationResponseDto save(ApplicationSaveRequestDto dto){
        return new ApplicationResponseDto(applicationRepository.save(dto.toEntity()));
    }

    public Optional<Application> getApplicationNo(Long no){return applicationRepository.findById(no);}
}
