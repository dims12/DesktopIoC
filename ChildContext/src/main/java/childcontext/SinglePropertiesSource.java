package childcontext;

import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;

/**
 * Created by Dims on 16.04.2017.
 */
public class SinglePropertiesSource extends MapPropertySource {

   public SinglePropertiesSource() {

      super("SinglePropertiesSource", new HashMap<>());
   }

   public HashMap<String, Object> getSource() {
      return (HashMap<String, Object>) super.getSource();
   }


}
