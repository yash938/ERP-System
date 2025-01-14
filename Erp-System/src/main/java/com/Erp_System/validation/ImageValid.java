package com.Erp_System.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValid.class)
public @interface ImageValid {
    // error message
    String message() default "Invalid Image Name..!";

    // represent group of constraints
    Class<?>[] groups() default { };

    // additional information about annotation..!
    Class<? extends Payload>[] payload() default  { };
}
