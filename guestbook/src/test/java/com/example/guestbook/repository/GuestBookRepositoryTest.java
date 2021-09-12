package com.example.guestbook.repository;

import com.example.guestbook.entity.GuestBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestBookRepositoryTest {

    @Autowired
    GuestBookRepository guestBookRepository;

    @Test
    @DisplayName("insert dummies")
    public void insertDummies() {
        //given

        IntStream.rangeClosed(1, 300).forEach(i ->{
            GuestBook guestBook = GuestBook.builder()
                    .title("Title..." + i)
                    .content("content..." + i)
                    .writer("writer..." + i)
                    .build();

            GuestBook saved = guestBookRepository.save(guestBook);

            System.out.println("saved = " + saved);
        });
        //when

        //then

    }

    @Test
    @DisplayName("update test")
    public void updateTest() {
        //given
        GuestBook guestBook = guestBookRepository.findById(300L).orElseThrow(EntityNotFoundException::new);

        //when
        guestBook.updateTitle("changed title...");
        guestBook.updateContent("changed content...");

        guestBookRepository.save(guestBook);
        //then

    }

}