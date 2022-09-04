package com.personal.transfer.producer.validators.annotations;

import com.personal.transfer.producer.validators.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
@Documented
public @interface ValidEnum {

    Class<? extends Enum<?>> value();

    String message() default "Invalid value. Should be: %s";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
