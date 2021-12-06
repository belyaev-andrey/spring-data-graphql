package org.haulmont.rnd.data.config;

import java.lang.annotation.*;

/**
 * EXPERIMENTAL, need to be able to define object name here.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GraphQlQuery {

    String value();

}