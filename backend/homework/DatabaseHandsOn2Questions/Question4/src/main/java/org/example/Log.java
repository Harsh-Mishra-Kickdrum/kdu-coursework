package org.example;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    LogLevel level() default LogLevel.INFO;
    String message() default "";
}
