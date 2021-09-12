package com.example.guestbook.service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageResultResponseDto;
import com.example.guestbook.entity.GuestBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GuestBookServiceTest {

    @Autowired
    GuestBookService guestBookService;

    @Test
    @DisplayName("register test")
    public void registerTest() {
        //given
        GuestBookDto.Request.Register register = GuestBookDto.Request.Register.builder()
                .title("Sample Title")
                .content("Sample Content")
                .writer("user0")
                .build();

        List<GuestBook> guestBookList = new ArrayList<>();

        //when
        Long savedId = guestBookService.register(register);


        //then

    }

    @Test
    @DisplayName("find page list")
    public void findPageList() {
        //given
        GuestBookDto.Request.SearchList query = GuestBookDto.Request.SearchList.builder()
                .pageNum(11)
                .amount(10)
                .sortCondition(GuestBookDto.Request.SearchList.SortCondition.GNO_ASC)
                .build();

        //when
        PageResultResponseDto<GuestBook> result = guestBookService.getList(query);

        //then
        System.out.println("result.getTotalPage() = " + result.getTotalPage());
        System.out.println("result.isPrev() = " + result.isPrev());
        System.out.println("result.isNext() = " + result.isNext());
        System.out.println("result.getStartPageNum() = " + result.getStartPageNum());
        System.out.println("result.getEndPageNum() = " + result.getEndPageNum());
        System.out.println("result.getNowPage() = " + result.getNowPage());

        List<GuestBook> contents = result.getContents();

        contents.forEach(guestBook -> System.out.println("guestBook = " + guestBook));

        result.getPageList().forEach(page -> System.out.println(page));


    }
}