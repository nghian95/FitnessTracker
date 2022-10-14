package com.nghianguyen.fitnesstracker.security;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * Custom Validator that will check if our form fields match. message() is what shows up when 
 * validation isn't passed. @Constraint defines that this will validate our fields. Validated 
 * by class FieldMatchValidator.
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch
{
   String message() default "{constraints.field-match}";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
   String first();
   String second();

   @Target({TYPE, ANNOTATION_TYPE})
   @Retention(RUNTIME)
   @Documented
   @interface List
   {
       FieldMatch[] value();
   }
}
