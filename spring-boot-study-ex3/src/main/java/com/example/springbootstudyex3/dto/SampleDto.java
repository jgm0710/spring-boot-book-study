package com.example.springbootstudyex3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class SampleDto {

    private Long sno;

    private String first;

    private String last;

    private LocalDateTime regDate;
}
