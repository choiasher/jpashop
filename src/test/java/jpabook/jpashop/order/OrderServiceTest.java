package jpabook.jpashop.order;

import jpabook.jpashop.common.AbstractSpringTest;
import jpabook.jpashop.member.Address;
import jpabook.jpashop.member.Member;
import jpabook.jpashop.member.MemberService;
import jpabook.jpashop.shop.Item;
import jpabook.jpashop.shop.ItemService;
import jpabook.jpashop.shop.NotEnoughStockException;
import jpabook.jpashop.shop.sub.Book;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("주문서비스 테스트")
@RequiredArgsConstructor
class OrderServiceTest extends AbstractSpringTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    EntityManager em;

    @Test
    void 주문검색() {
        //Given
        int orderCount = 2;
        int stockQuantity = 10;
        BigDecimal itemPrice = BigDecimal.valueOf(10000);

        Member member = Member.createMember("회원1", new Address("서울", "강가", "123-123"));
        memberService.save(member);
        Item item = Book.createBook("시골 JPA", itemPrice, stockQuantity);
        itemService.save(item);
        em.flush();

        //When
        Order order = orderService.order(member.getId(), item.getId(), orderCount);
        em.flush();

        //Then
        Order.Search searchDTO = new Order.Search();
        searchDTO.setOrderStatus(OrderStatus.ORDER);
        searchDTO.setMemberName("회원1");

        Assertions.assertAll(
                () -> assertFalse(orderService.search(searchDTO, Pageable.unpaged()).isEmpty())
        );
    }

    @Test
    void 상품주문() {

        //Given
        int orderCount = 2;
        int stockQuantity = 10;
        BigDecimal itemPrice = BigDecimal.valueOf(10000);

        Member member = Member.createMember("회원1", new Address("서울", "강가", "123-123"));
        memberService.save(member);
        Item item = Book.createBook("시골 JPA", itemPrice, stockQuantity);
        itemService.save(item);
        em.flush();

        //When
        Order order = orderService.order(member.getId(), item.getId(), orderCount);
        em.flush();

        //Then
        Order createdOrder = orderService.findById(order.getId());

        Assertions.assertAll(
                () -> assertEquals(OrderStatus.ORDER, createdOrder.getStatus(),
                        "상품 주문시 상태는 ORDER"),
                () -> assertEquals(1, createdOrder.getOrderItems().size(),
                        "주문한 상품 종류 수가 정확해야 한다."),
                () -> assertEquals(itemPrice.multiply(BigDecimal.valueOf(orderCount)), createdOrder.getTotalPrice(),
                        "주문 가격은 가격 * 수량이다."),
                () -> assertEquals(stockQuantity - orderCount, item.getStockQuantity(),
                        "주문 수량만큼 재고가 줄어야 한다.")
        );
    }

    @Test
    void 상품주문_재고_수량초과() {

        //Given
        int stockQuantity = 10;
        BigDecimal itemPrice = BigDecimal.valueOf(10000);

        Member member = Member.createMember("회원1", new Address("서울", "강가", "123-123"));
        memberService.save(member);
        Item item = Book.createBook("시골 JPA", itemPrice, stockQuantity);
        itemService.save(item);
        em.flush();

        //When Then
        assertThrows(NotEnoughStockException.class, () ->
                        orderService.order(member.getId(), item.getId(), stockQuantity + 1)
                , "재고보다 주문수량이 많았지만 주문이 완료됨");
    }

    @Test
    void 주문취소() {

        //Given
        int orderCount = 2;
        int stockQuantity = 10;
        BigDecimal itemPrice = BigDecimal.valueOf(10000);

        Member member = Member.createMember("회원1", new Address("서울", "강가", "123-123"));
        memberService.save(member);
        Item item = Book.createBook("시골 JPA", itemPrice, stockQuantity);
        itemService.save(item);
        em.flush();

        Order order = orderService.order(member.getId(), item.getId(), orderCount);
        em.flush();

        // When
        orderService.cancelOrder(order.getId());
        em.flush();

        // Then
        Order canceledOrder = orderService.findById(order.getId());

        Assertions.assertAll(
                () -> assertEquals(OrderStatus.CANCEL, canceledOrder.getStatus(),
                        "주문 취소시 상태는 CANCEL 이다."),
                () -> assertEquals(stockQuantity, item.getStockQuantity(),
                        "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.")
        );
    }


}