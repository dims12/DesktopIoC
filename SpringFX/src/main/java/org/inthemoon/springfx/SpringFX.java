package org.inthemoon.springfx;

import childcontext.ChildContextFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dims on 25.03.2017.
 */
public class SpringFX extends ChildContextFactory {


   @Override
   public AnnotationConfigApplicationContext createChildContext() {
      register(FXConfig.class);
      return super.createChildContext();
   }

   public SpringFX(Class<?>... annotatedClasses) {
      super(annotatedClasses);
   }

   public SpringFX(String basePackage, String... restBasePackages) {
      super(basePackage, restBasePackages);
   }



}
