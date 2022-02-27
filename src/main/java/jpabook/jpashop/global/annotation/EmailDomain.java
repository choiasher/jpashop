package jpabook.jpashop.global.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailDomainValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailDomain {

    String message() default "이메일 도메인 형식이 아닙니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}
