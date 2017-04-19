package org.inthemoon.springfx.fxml;

import javafx.fxml.FXMLLoader;
import org.inthemoon.springfx.SpringFXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Dims on 08.04.2017.
 */
@Configuration
public class FXMLConfig {

   @Autowired
   ApplicationContext context;

   @Bean
   ControllerFactory controllerFactory() {
      return new ControllerFactory();
   }


   @Bean
   FXMLLoader fxmlLoader() throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setControllerFactory(controllerFactory());
      String fxmlLocation = context.getEnvironment().getProperty(SpringFXML.fxmlLocationPropertyName);
      if( fxmlLocation != null ) {
         fxmlLoader.setLocation(context.getResource(fxmlLocation).getURL());
      }

      return fxmlLoader;
   }

   @Bean
   FXMLLoaderProxy fxmlLoaderProxy() throws IOException {
      FXMLLoaderProxy ans = new FXMLLoaderProxy(fxmlLoader());
      return ans;
   }


}
