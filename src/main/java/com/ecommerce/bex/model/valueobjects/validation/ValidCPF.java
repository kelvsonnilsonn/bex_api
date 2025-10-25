package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * ISSO SE CHAMA 'CUSTOM VALIDATION ANNOTATION'
 * É CRIADO ATRAVÉS DO JAKARTA BEAN VALIDATION
 * <p>
 * A CONSTRAINT CUSTOMIZADA!
 * */

@Documented
@Constraint(validatedBy = CPFValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCPF {
    String message() default "O CPF não segue um padrão válido (XXX.XXX.XXX-XX ou XXXXXXXXXXX)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
