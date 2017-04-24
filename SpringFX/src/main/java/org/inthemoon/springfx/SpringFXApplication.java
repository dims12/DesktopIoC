package org.inthemoon.springfx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Dims on 24.04.2017.
 */
public class SpringFXApplication extends SpringFX {

   public SpringFXApplication(Class<?>... annotatedClasses) {
      super(annotatedClasses);
   }

   public SpringFXApplication(String basePackage, String... restBasePackages) {
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


   private static AnnotationConfigApplicationContext staticChildContext;

   public static AnnotationConfigApplicationContext getStaticChildContext() {
      return staticChildContext;
   }

   public static class App extends Application {

      @Override
      public void start(Stage primaryStage) throws Exception {

         staticChildContext.refresh();

         StageService stageService = staticChildContext.getBean(StageService.class);
         stageService.configureStage( primaryStage );

      }
   }

}

