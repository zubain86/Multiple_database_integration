package com.example.mdi.configuration.annotation;

import com.example.mdi.configuration.ClientNames;
import com.example.mdi.configuration.DBContextHolder;
import org.hibernate.annotations.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReadOnly {

}
