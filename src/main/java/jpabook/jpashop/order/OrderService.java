package jpabook.jpashop.order;

import jpabook.jpashop.delivery.Delivery;
import jpabook.jpashop.member.Member;
import jpabook.jpashop.member.MemberService;
import jpabook.jpashop.shop.Item;
import jpabook.jpashop.shop.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    @Transactional
    public Order order(Long memberId, Long itemId, int count) {
        Member member = memberService.findById(memberId);
        Item item = itemService.findById(itemId);

        Delivery delivery = Delivery.createDelivery(member.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item, count);
        Order order = Order.createOrder(member, delivery, orderItem);

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<Order> search(Order.Search orderSearch, Pageable pageable) {
        return orderRepository.search(orderSearch, pageable);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = findById(orderId);
        order.cancel();
    }
}
