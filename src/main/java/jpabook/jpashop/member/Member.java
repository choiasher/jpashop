package jpabook.jpashop.member;

import jpabook.jpashop.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Getter
    static final class CreateRequest {
        @NotBlank
        private String name;
        private Address address;
    }

    @Getter
    static final class CreateResponse {
        private final String name;
        private final Address address;

        public CreateResponse(Member member) {
            this.name = member.getName();
            this.address = member.getAddress();
        }
    }


    public static Member createMember(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        return member;
    }

    public static Member createMember(CreateRequest signUpRequest) {
        Member member = new Member();
        member.setName(signUpRequest.getName());
        member.setAddress( signUpRequest.getAddress());
        return member;
    }


}
