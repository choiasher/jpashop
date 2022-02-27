package jpabook.jpashop.global.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNickNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueNickName {

    String message() default "이미 사용중인 닉네임입니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}
