package jpabook.jpashop.order;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.global.queryutil.QEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static jpabook.jpashop.global.queryutil.QueryUtil.pathEqual;
import static jpabook.jpashop.global.queryutil.QueryUtil.pathLike;

@Repository
public class OrderQueryRepositoryImpl extends QuerydslRepositorySupport implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager em) {
        super(Order.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional(readOnly = true)
    public Page<Order> search(Order.Search orderSearch, Pageable pageable) {

        JPAQuery<Order> query = queryFactory
                .select(QEntity.order)
                .from(QEntity.order)
                .innerJoin(QEntity.member)
                .on(QEntity.order.member.eq(QEntity.member))
                .where(
                        pathEqual(QEntity.order.status, orderSearch.getOrderStatus()),
                        pathLike(QEntity.member.name, orderSearch.getMemberName())
                );

        if (pageable.isPaged()) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());
        }

        JPAQuery<Long> countQuery = queryFactory
                .select(QEntity.order.count())
                .from(QEntity.order)
                .innerJoin(QEntity.member)
                .on(QEntity.order.member.eq(QEntity.member))
                .where(
                        pathEqual(QEntity.order.status, orderSearch.getOrderStatus()),
                        pathLike(QEntity.member.name, orderSearch.getMemberName())
                );

        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchOne);
    }


}
