package com.ecommerce.bex.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "O email inserido não segue o padrão de emails (seuemail@dominio.XXX, permitindo o uso de . e _)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
