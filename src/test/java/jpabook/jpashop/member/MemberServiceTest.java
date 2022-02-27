package jpabook.jpashop.member;

import jpabook.jpashop.common.AbstractSpringTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

class MemberServiceTest extends AbstractSpringTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원이 가입한다")
    @Test
    void join() {

        //given
        Member member = Member.createMember("kevin", new NullAddress());

        //when
        Long savedMemberId = memberService.join(member);

        //then
        Assertions.assertEquals(savedMemberId, member.getId());
    }

    @DisplayName("중복된 회원이 있으면 예외가 발생한다")
    @Test
    void validateDuplicatedMember() {

        //given
        Member member = Member.createMember("kevin", new NullAddress());
        memberRepository.save(member);

        //when
        Executable executable = () -> memberService.join(member);

        //then
        Assertions.assertThrows(MemberDuplicatedException.class, executable);

    }
}