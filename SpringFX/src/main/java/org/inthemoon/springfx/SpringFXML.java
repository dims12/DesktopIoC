package org.inthemoon.springfx;

import childcontext.ChildContextFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dims on 14.04.2017.
 */
public class SpringFXML extends ChildContextFactory {

   public static final String fxmlLocationPropertyName = "fxmlLocation";



   @Override
   public void disposeLastChildContext() {
      super.disposeLastChildContext();
   }

   @Override
   public AnnotationConfigApplicationContext createChildContext() {
      register(FXConfig.class);
      return super.createChildContext();
   }
}
