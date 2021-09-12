package com.example.guestbook.repository;

import com.example.guestbook.entity.GuestBook;
import net.bytebuddy.TypeCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>{
}
