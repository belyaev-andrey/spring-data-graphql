package org.haulmont.rnd.data.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GraphQlQuery {

    String value();

}