package jpabook.jpashop.global.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    private static final String EMAIL_PATTERN = "@(\\w+.\\w+)";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        return email.matches(EMAIL_PATTERN);
    }
}
