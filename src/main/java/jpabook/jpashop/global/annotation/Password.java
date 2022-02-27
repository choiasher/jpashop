package jpabook.jpashop.global.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Password {

    String message() default "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 6자 이상 20자 이하의 비밀번호로 설정해주세요.";

    Class[] groups() default {};

    Class[] payload() default {};
}
