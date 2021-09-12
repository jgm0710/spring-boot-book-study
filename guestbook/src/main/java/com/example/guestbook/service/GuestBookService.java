package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageResultResponseDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookQueryRepository;
import com.example.guestbook.repository.GuestBookRepository;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Log4j2
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;

    private final GuestBookQueryRepository guestBookQueryRepository;

    public Long register(GuestBookDto.Request.Register registerDto) {
        log.info("DTO.....................");
        log.info(registerDto);

        GuestBook guestBook = registerDto.toEntity();
        log.info(guestBook);

        return guestBookRepository.save(guestBook).getId();
    }

    public PageResultResponseDto<GuestBook> getList(GuestBookDto.Request.SearchList searchListDto) {
        QueryResults<GuestBook> result = guestBookQueryRepository.findList(searchListDto);

        return new PageResultResponseDto<>(
                result.getResults(),
                result.getTotal(),
                searchListDto.getPageNum(),
                searchListDto.getAmount()
        );
    }

    public GuestBook getOne(Long gno) {
        return guestBookRepository.findById(gno).orElseThrow(EntityNotFoundException::new);
    }

    public void Update(GuestBookDto.Request.Modify modifyDto) {
        GuestBook guestBook = guestBookRepository.findById(modifyDto.getGno()).orElseThrow(EntityNotFoundException::new);
        guestBook.modify(modifyDto.getTitle(), modifyDto.getContent());

        guestBookRepository.save(guestBook);
    }

    public void remove(Long gno) {
        GuestBook guestBook = guestBookRepository.findById(gno).orElseThrow(EntityNotFoundException::new);
        guestBookRepository.delete(guestBook);
    }
}
