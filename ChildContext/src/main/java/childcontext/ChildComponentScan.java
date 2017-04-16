package childcontext;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(ChildComponentScans.class)
public @interface ChildComponentScan {

	String[] value() default {};
}