package com.example.guestbook.dto;

import com.example.guestbook.entity.GuestBook;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class GuestBookDto {

    public static class Request {

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Register {
            private String title;
            private String content;
            private String writer;

            public GuestBook toEntity() {
                return GuestBook.builder()
                        .title(title)
                        .content(content)
                        .writer(writer)
                        .build();
            }
        }

        @Getter
        public static class SearchList extends PageRequestDto{

            private SortCondition sortCondition;

            @Builder
            public SearchList(int pageNum, int amount, SortCondition sortCondition) {
                super(pageNum, amount);
                this.sortCondition = sortCondition;
            }

            public SearchList() {
                super(1, 10);
                this.sortCondition = SortCondition.GNO_DESC;
            }

            public enum SortCondition {
                GNO_DESC,
                GNO_ASC,
                ;
            }
        }


        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Modify {
            private Long gno;
            private String title;
            private String content;
        }


    }

    public static class Response {

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Simple {
            private Long gno;
            private String title;
            private String content;
            private String writer;
            private String regDate;

            public static Simple of(GuestBook guestBook) {
                return Simple.builder()
                        .gno(guestBook.getId())
                        .title(guestBook.getTitle())
                        .content(guestBook.getContent())
                        .writer(guestBook.getWriter())
                        .regDate(guestBook.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .build();
            }
        }

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Detail {
            private Long gno;
            private String title;
            private String content;
            private String writer;
            private String regDate;
            private String modDate;

            public static Detail of(GuestBook guestBook) {
                return Detail.builder()
                        .gno(guestBook.getId())
                        .title(guestBook.getTitle())
                        .content(guestBook.getContent())
                        .writer(guestBook.getWriter())
                        .regDate(guestBook.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .modDate(guestBook.getModDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .build();
            }
        }
    }
}
