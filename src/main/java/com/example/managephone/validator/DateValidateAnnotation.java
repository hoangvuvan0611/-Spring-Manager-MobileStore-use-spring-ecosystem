package com.example.managephone.validator;

import jakarta.validation.Payload;

public @interface DateValidateAnnotation {
    String message() default "Default message date not valid";

    Class<?> [] groups() default {};
    Class< ? extends Payload> [] payload() default {};
}
