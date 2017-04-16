package childcontext;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ChildContexts.class)
public @interface ChildContext {
   Class<?>[] value();
}