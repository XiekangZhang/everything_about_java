package de.xiekang.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UniqueConstraint {
    String name() default "";
    String[] columnNames();
}
