package com.example.springbootstudyex1.repository;

import com.example.springbootstudyex1.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    @DisplayName("bulk save test")
    public void saveTest() {
        //given
        IntStream.rangeClosed(1,100).forEach(value -> {
            Memo memo = Memo.builder()
                    .text("Sample..." + value)
                    .build();
            memoRepository.save(memo);
        });

        //when

        //then

    }

    @Test
    @DisplayName("find by id test")
    public void findByIdTest() {
        //given
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("========================================================================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println("memo = " + memo);
        }
        //when

        //then

    }

    @Transactional
    @Test
    @DisplayName("get one test")
    public void getOneTest() {
        //given
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("========================================================================");
        System.out.println("memo = " + memo);
        //when

        //then

    }

}