package jpabook.jpashop.global.queryutil;

import jpabook.jpashop.member.QMember;
import jpabook.jpashop.order.QOrder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QEntity {
    public static final QOrder order = QOrder.order;
    public static final QMember member = QMember.member;
}
