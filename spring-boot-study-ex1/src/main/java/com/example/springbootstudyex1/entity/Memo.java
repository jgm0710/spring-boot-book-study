package com.example.springbootstudyex1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_memo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mno")
    private Long id;

    @Column(length = 200, nullable = false, name = "memo_text")
    public String text;

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
