package jpabook.jpashop.global;

import jpabook.jpashop.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Profile(value = {"local"})
@Component
@RequiredArgsConstructor
public class DataLoader {

    private final MemberInitService roleInitService;

    @PostConstruct
    public void init() {
        roleInitService.init();
    }

    @RequiredArgsConstructor
    @Component
    static class MemberInitService {
        private final MemberService memberService;

        @Transactional
        public void init() {

        }
    }
}