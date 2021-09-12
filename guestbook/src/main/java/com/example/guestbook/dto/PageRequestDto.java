package com.example.guestbook.dto;


import lombok.Data;

@Data
public abstract class PageRequestDto {

    private int pageNum = 1;

    private int amount = 10;

    public PageRequestDto(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getOffset() {
        return (pageNum - 1) * amount;
    }

}
