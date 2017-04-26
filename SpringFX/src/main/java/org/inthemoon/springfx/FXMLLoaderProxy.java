package org.inthemoon.springfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

/**
 * If this bean is present in context, it creates {@link FXMLLoader} and
 * initilizes it's
 *
 * Created by Dims on 19.04.2017.
 */
public class FXMLLoaderProxy {

   private final ApplicationContext applicationContext;

   @Autowired
   public FXMLLoaderProxy(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
   }


   private FXMLLoader fxmlLoader;

   private FXMLLoader getFxmlLoader() {
      if( fxmlLoader == null ) {
         fxmlLoader = new FXMLLoader();
      }
      return fxmlLoader;
   }

   public FXControllerFactory getControllerFactory() {
      if( fxmlLoader != null ) {
         return (FXControllerFactory) fxmlLoader.getControllerFactory();
      }
      else {
         return null;
      }
   }

   @Autowired(required = false)
   public void setControllerFactory(FXControllerFactory FXControllerFactory) {
      getFxmlLoader().setControllerFactory(FXControllerFactory);
   }



   public String getLocation() {
      if( fxmlLoader != null ) {
         return fxmlLoader.getLocation().toString();
      }
      else {
         return null;
      }
   }

   @Value("${fxmlLocation:#{null}}")
   public void setLocation(String location) {
      if( location != null ) {
         try {
            URL locationURL = applicationContext.getResource(location).getURL();
            if( locationURL != null ) {
               getFxmlLoader().setLocation(locationURL);
            }
            else {
               String msg = String.format(Locale.US, "fxmlLocation %s was not found", location);
               throw new RuntimeException(msg);
            }
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
      else {
         if( fxmlLoader != null ) {
            fxmlLoader.setLocation(null);
         }
      }
   }

   public boolean hasLocation() {
      return getLocation() != null;
   }



   public <T> T getRoot() {
      T ans = getFxmlLoader().getRoot();
      if( ans == null ) {
         try {
            ans = getFxmlLoader().load();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
      return ans;
   }

   public <T> T getController() {
      T ans = getFxmlLoader().getController();
      if( ans == null ) {
         try {
            ans = getFxmlLoader().load();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
      return ans;
   }

   private Scene scene = null;

   public Scene getScene() {
      if( scene == null ) {
         scene = new Scene(getRoot());
      }
      return scene;
   }
}
