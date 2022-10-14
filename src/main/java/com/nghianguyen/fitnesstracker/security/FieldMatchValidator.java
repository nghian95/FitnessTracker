package com.nghianguyen.fitnesstracker.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

/*
 * Validator class that enforces the rules of the FieldMatch validation.
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
   private String firstFieldName;
   private String secondFieldName;

   /*
    * Initializes the firstFieldName and secondFieldName variables based on the FieldMatch
    * values passed. These will be the two fields we need to match.
    */
   @Override
   public void initialize(final FieldMatch constraintAnnotation) {
       firstFieldName = constraintAnnotation.first();
       secondFieldName = constraintAnnotation.second();
   }

   /*
    * Method where we define our validation rules. Gets the fields values using BeanUtils.getProperty
    * and compares them. If both are null or both equal each other then no error will occur and it'll 
    * be a valid field combination.
    */
   @Override
   public boolean isValid(final Object value, final ConstraintValidatorContext context) {
       try {
           final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
           final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
           return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
       } catch (final Exception ignore) {}
       return true;
   }
}
