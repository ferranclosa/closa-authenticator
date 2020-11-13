package com.closa.global.validators;

import javax.validation.Validation;
import javax.validation.Validator;

public class Validators {
    private static Validator validator;

    public Validators() {
    }

    public static Validator getValidator(){
        if(validator == null ){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        return validator;
    }
}
