package jpabook.jpashop.member;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class NullAddress extends Address {

    public NullAddress() {
        //do nothing
    }
}