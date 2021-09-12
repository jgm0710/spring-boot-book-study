package com.example.guestbook.dto;

import com.example.guestbook.common.Convertor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Data
@NoArgsConstructor
public class PageResultResponseDto<T> {

    private List<T> contents;

    private long totalPage;
    private long totalResultsCount;

    private long nowPage;

    private int requestAmount;

    private long startPageNum;
    private long endPageNum;

    private boolean prev;
    private boolean next;

    private List<Long> pageList;

    public PageResultResponseDto(List<T> contents, long totalResultsCount, long nowPage, int requestAmount) {
        this.contents = contents;
        this.totalResultsCount = totalResultsCount;
        this.totalPage = calculateTotalPagesCount(totalResultsCount);
        this.nowPage = nowPage;
        this.requestAmount = requestAmount;

        this.startPageNum = calculateStartPageNum(nowPage);
        this.endPageNum = calculateEndPageNum(nowPage, totalPage);
        this.prev = hasPrev(startPageNum);
        this.next = hasNext(nowPage, totalPage);
        this.pageList = createPageList(startPageNum,endPageNum);
    }

    private long calculateTotalPagesCount(long totalResultsCount) {
        return (totalResultsCount / 10) + 1;
    }

    private List<Long> createPageList(long startPageNum, long endPageNum) {
        return LongStream.rangeClosed(startPageNum, endPageNum).boxed().collect(Collectors.toList());
    }

    private boolean hasNext(long nowPage, long totalPage) {
        int tmpEndPage = calculateTmpEndPage(nowPage);
        return totalPage > tmpEndPage;
    }

    private boolean hasPrev(long startPageNum) {
        return startPageNum > 1;
    }

    private long calculateEndPageNum(long nowPage, long totalPage) {
        int tmpEndPage = calculateTmpEndPage(nowPage);
        return totalPage > tmpEndPage ? tmpEndPage : totalPage;
    }

    private int calculateStartPageNum(long nowPage) {
        return calculateTmpEndPage(nowPage) - 9;
    }

    private int calculateTmpEndPage(long nowPage) {
        return (int) (Math.ceil(nowPage / 10.0) * 10);
    }

    public <R> PageResultResponseDto<R> convert(Convertor<T, R> convertor) {
        return new PageResultResponseDto<R>(
                convertContents(convertor),
                totalResultsCount,
                nowPage,
                requestAmount);
    }

    private <R> List<R> convertContents(Convertor<T, R> convertor) {
        return !CollectionUtils.isEmpty(contents)
                ? contents.stream().map(convertor::convert).collect(Collectors.toList()) : null;
    }

}
