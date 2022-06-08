package com.dormabook.service.application;

import com.dormabook.domain.application.Application;
import com.dormabook.domain.application.ApplicationRepository;
import com.dormabook.web.dto.application.ApplicationResponseDto;
import com.dormabook.web.dto.application.ApplicationSaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<GetApplicationResponse> findByIdApplicationList(String userId){
        return applicationRepository.findByIdApplicationList(userId);
    }

    public GetAppContentResponse getByAppContent(String applicationNo){
        return applicationRepository.getByAppContent(applicationNo);
    }

    public int modifyByAppContent(Long applicationNo, int flag){
        return applicationRepository.modifyByAppContent(applicationNo, flag);
    }
    public ApplicationResponseDto save(ApplicationSaveRequestDto dto){
        return new ApplicationResponseDto(applicationRepository.save(dto.toEntity()));
    }

    public Optional<Application> getApplicationNo(Long no){return applicationRepository.findById(no);}
}
