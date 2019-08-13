package com.gjt.jdk8;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TestRepeatAnnotation {
    
    @Target({ElementType.TYPE,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter{
        String value();
    }
    
    @Target({ElementType.TYPE,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters{
        Filter[] value();
    }
    
    @Filter("filter1")
    @Filter("filter2")
    public interface DoFilter {
        
    }
    
    public static void main(String[] args) {
        for(Filter a : DoFilter.class.getAnnotationsByType(Filter.class)) {
            System.out.println(a.value());
        }
        
    }
    
}
