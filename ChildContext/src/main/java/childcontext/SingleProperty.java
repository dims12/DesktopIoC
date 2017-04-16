package childcontext;

import java.lang.annotation.*;

/**
 * Allows to set single explicit property from annotation
 *
 * Created by Dims on 16.04.2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = SingleProperties.class)
public @interface SingleProperty {
   String key();
   String value();
}
