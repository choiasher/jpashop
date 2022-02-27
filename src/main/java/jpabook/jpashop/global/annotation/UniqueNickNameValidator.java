package jpabook.jpashop.global.annotation;

import jpabook.jpashop.global.AppContextHolder;
import jpabook.jpashop.member.MemberRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueNickNameValidator implements ConstraintValidator<UniqueNickName, String> {

    @Override
    public boolean isValid(String nickName, ConstraintValidatorContext constraintValidatorContext) {
        if (nickName == null || nickName.isEmpty()) {
            return true;
        }
        return !AppContextHolder.getBean(MemberRepository.class).existsByName(nickName);
    }
}