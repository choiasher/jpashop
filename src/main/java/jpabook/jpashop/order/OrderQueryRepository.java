package jpabook.jpashop.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository {
    Page<Order> search(Order.Search orderSearch, Pageable pageable);
}
