package jpabook.jpashop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("orders/")
@RestController
public class OrderController {

    private final OrderService orderService;

}
