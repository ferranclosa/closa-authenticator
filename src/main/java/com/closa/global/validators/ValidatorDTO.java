package com.closa.global.validators;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.exceptions.InvalidInputFormat;


import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidatorDTO {
    public static <I> void validate(I pInput) throws AppException{
        Set<ConstraintViolation<I>> constraintViolations = Validators.getValidator().validate(pInput);
        if(!constraintViolations.isEmpty()){
            ConstraintViolation<I> constraint = constraintViolations.iterator().next();
            throw new InvalidInputFormat(constraint.getPropertyPath().toString() + constraint.getMessage());
        }
    }

}
