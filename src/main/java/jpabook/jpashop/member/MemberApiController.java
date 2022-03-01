package jpabook.jpashop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/sign-up")
    public Member.CreateResponse signUp(@RequestBody @Valid Member.CreateRequest createRequest) {
        return new Member.CreateResponse(memberService.signUp(createRequest));
    }
}
