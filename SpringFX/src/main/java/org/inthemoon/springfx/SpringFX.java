package org.inthemoon.springfx;

import childcontext.ChildContextFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import org.inthemoon.springfx.fx.FXConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dims on 25.03.2017.
 */
public class SpringFX extends ChildContextFactory {


   private static AnnotationConfigApplicationContext staticChildContext;


   public static class App extends Application {

      @Override
      public void start(Stage primaryStage) throws Exception {

         staticChildContext.refresh();

         StageService configurer = staticChildContext.getBean(StageService.class);
         configurer.setStage( primaryStage );

      }
   }

   @Override
   public AnnotationConfigApplicationContext createChildContext() {
      AnnotationConfigApplicationContext childContext = super.createChildContext();
      childContext.register(FXConfig.class);
      return childContext;
   }

   public SpringFX(Class<?>... annotatedClasses) {
      super(annotatedClasses);
   }

   public SpringFX(String basePackage, String... restBasePackages) {
      super(basePackage, restBasePackages);
   }

   public void launch(String[] args) {
      if( getLastChildContext() == null ) {
         createChildContext();
         staticChildContext = getLastChildContext();

         Application.launch(App.class, args);
      }
      else {
         throw new IllegalStateException("JavaFX already started");
      }
   }

}
