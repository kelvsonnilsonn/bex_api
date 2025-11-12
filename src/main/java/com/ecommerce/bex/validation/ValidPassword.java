package com.ecommerce.bex.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "A senha não está boa o suficiente. Deve ter mais de 6 caracteres e ao menos 1 letra maiúscula e 1 digito.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
