package com.example.mdi.configuration.annotation;

import com.example.mdi.configuration.ClientNames;
import com.example.mdi.configuration.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationConfiguration {

    @Before("@annotation(ReadOnly)")
     public void setReadOnly() {
          DBContextHolder.setCurrentDb(ClientNames.DB2);
     }

     @Before("@annotation(WriteOnly)")
    public void setWriteOnly() {
            DBContextHolder.setCurrentDb(ClientNames.DB1);
        }

}
