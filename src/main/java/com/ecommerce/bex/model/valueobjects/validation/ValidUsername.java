package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    String message() default "O nome de usuário deve possuir mais de 3 letras.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
