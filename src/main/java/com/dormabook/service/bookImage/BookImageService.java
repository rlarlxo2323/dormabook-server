package com.dormabook.service.bookImage;

import com.dormabook.domain.bookImage.BookImageRepository;
import com.dormabook.web.dto.bookImage.BookImageResponseDto;
import com.dormabook.web.dto.bookImage.BookImageSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookImageService {

    private final BookImageRepository bookImageRepository;

    public BookImageResponseDto save(BookImageSaveRequestDto dto){
        return new BookImageResponseDto(bookImageRepository.save(dto.toEntity()));
    }
}
