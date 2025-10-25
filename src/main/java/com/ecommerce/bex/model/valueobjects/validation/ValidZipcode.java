package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZipcodeValidator.class)
public @interface ValidZipcode {
    String message() default "O CEP inserido não segue um padrão válido (XXXX-XXX ou XXXXXXX)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
