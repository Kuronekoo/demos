package cn.sc.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface AgeValidator {

   int min();
   int max();

}
