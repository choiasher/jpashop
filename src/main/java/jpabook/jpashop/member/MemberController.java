package jpabook.jpashop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUp(@Valid Member.SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return "redirect:/";
    }
}
