package com.personal.transfer.producer.validators;

import com.personal.transfer.producer.validators.annotations.ValidEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Class<?> enumToValidate;
    private String messageBase;


    @Override
    public void initialize(final ValidEnum constraintAnnotation) {

        enumToValidate = constraintAnnotation.value();
        messageBase = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(final String fieldValue, final ConstraintValidatorContext context) {

        final List<String> possibleValues = Arrays.stream(enumToValidate.getEnumConstants()).map(Objects::toString).collect(Collectors.toList());

        if (possibleValues.contains(fieldValue)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(String.format(messageBase, possibleValues)).addConstraintViolation();
        context.disableDefaultConstraintViolation();

        return false;
    }
}
