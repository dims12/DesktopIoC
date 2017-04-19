package org.inthemoon.springfx.fxml;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by Dims on 19.04.2017.
 */
public class FXMLLoaderProxy {

   private final FXMLLoader fxmlLoader;

   public FXMLLoaderProxy(FXMLLoader fxmlLoader) {
      this.fxmlLoader = fxmlLoader;
   }

   public <T> T getRoot() {
      T ans = fxmlLoader.getRoot();
      if( ans == null ) {
         try {
            ans = fxmlLoader.load();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
      return ans;
   }

   public <T> T getController() {
      T ans = fxmlLoader.getController();
      if( ans == null ) {
         try {
            ans = fxmlLoader.load();
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
      return ans;
   }
}
