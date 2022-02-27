package jpabook.jpashop.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {

    Page<Order> search(Order.Search orderSearch, Pageable pageable);
}
