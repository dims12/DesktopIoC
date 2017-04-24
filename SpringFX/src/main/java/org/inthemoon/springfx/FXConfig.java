package org.inthemoon.springfx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Dims on 08.04.2017.
 */
@Configuration
public class FXConfig {

   @Autowired
   AnnotationConfigApplicationContext applicationContext;

   @Bean
   StageService stageService() {
      return new StageService();
   }

   @Bean
   FXMLLoaderProxy fxmlLoaderProxy() throws IOException {
      return new FXMLLoaderProxy(applicationContext);
   }

   @Bean
   FXControllerFactory controllerFactory() {
      return new FXControllerFactory(applicationContext);
   }

}
