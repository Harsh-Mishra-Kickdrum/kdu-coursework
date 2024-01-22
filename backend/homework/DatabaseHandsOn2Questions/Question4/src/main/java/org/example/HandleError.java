package org.example;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HandleError {
    Class<? extends Exception>[] value() default {};
}

