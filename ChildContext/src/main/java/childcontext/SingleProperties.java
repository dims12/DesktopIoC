package childcontext;

import java.lang.annotation.*;

/**
 * Created by Dims on 16.04.2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleProperties {
   SingleProperty[] value();
}
