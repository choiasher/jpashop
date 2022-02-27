package jpabook.jpashop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Transactional
    public Long join(Member member) {
        this.validateDuplicatedMember(member);
        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public void validateDuplicatedMember(Member member) {
        if (!memberRepository.findByName(member.getName()).isEmpty()) {
            throw new MemberDuplicatedException();
        }
    }

    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public Member signUp(Member.SignUpRequest signUpRequest) {
        return memberRepository.save(Member.createMember(signUpRequest));
    }
}
