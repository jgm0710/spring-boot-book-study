package com.example.guestbook.repository;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.entity.GuestBook;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.example.guestbook.entity.QGuestBook.guestBook;

@Repository
@RequiredArgsConstructor
public class GuestBookQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QueryResults<GuestBook> findList(GuestBookDto.Request.SearchList query) {
        return queryFactory.selectFrom(guestBook)
                .offset(query.getOffset())
                .limit(query.getAmount())
                .orderBy(
                        sortEq(query.getSortCondition())
                )
                .fetchResults();
    }

    private OrderSpecifier<?> sortEq(GuestBookDto.Request.SearchList.SortCondition sortCondition) {
        switch (sortCondition) {
            case GNO_ASC:
                return guestBook.id.asc();
            default:
                return guestBook.id.desc();
        }
    }
}
