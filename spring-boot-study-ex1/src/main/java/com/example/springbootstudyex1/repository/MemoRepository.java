package com.example.springbootstudyex1.repository;

import com.example.springbootstudyex1.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
