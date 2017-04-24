package org.inthemoon.springfx;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This is {@link Stage} configurer. This bean has {@link #configureStage(Stage)}
 * method, on which it configures assigned Stage, taking required beans
 * and parameters from context</p>
 *
 * This bean is used to configure {@code primaryStage}, but it also can be used
 * to configure any stages in child contexts.</p>
 *
 * Finally it can be uses to create and configure new Stage at once with {@link #createStage()}
 * methods.
 *
 * Created by Dims on 25.03.2017.
 */
public class StageService  {

   private final ApplicationContext applicationContext;

   @Autowired
   public StageService(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
   }


   private ShowAfterConfigure showAfterConfigure = ShowAfterConfigure.Show;

   public ShowAfterConfigure getShowAfterConfigure() {
      return showAfterConfigure;
   }

   @Autowired(required = false)
   public void setShowAfterConfigure(ShowAfterConfigure showAfterConfigure) {
      this.showAfterConfigure = showAfterConfigure;
   }



   private Modality modality;

   public Modality getModality() {
      return modality;
   }

   @Autowired(required = false)
   public void setModality(Modality modality) {
      this.modality = modality;
   }




   private Scene scene;

   public Scene getScene() {
      return scene;
   }

   @Autowired(required = false)
   public void setScene(Scene scene) {
      this.scene = scene;
   }



   private FXMLLoaderProxy fxmlLoaderProxy;

   public FXMLLoaderProxy getFxmlLoaderProxy() {
      return fxmlLoaderProxy;
   }

   @Autowired(required = false)
   public void setFxmlLoaderProxy(FXMLLoaderProxy fxmlLoaderProxy) {
      this.fxmlLoaderProxy = fxmlLoaderProxy;
   }




   private String title;

   public String getTitle() {
      return title;
   }

   @Value("${stage.title:#{'No Title'}}")
   public void setTitle(String title) {
      this.title = title;
   }



   private Stage lastStage;

   public Stage getLastStage() {
      return lastStage;
   }

   public void configureStage(Stage stage) {
      this.lastStage = stage;

      if( stage == null ) {
         throw new NullPointerException();
      }

      if( getModality() != null ) {
         stage.initModality(getModality());
      }

      if( getTitle() != null ) {
         stage.setTitle(title);
      }


      if( getScene() == null ) {
         throw new NullPointerException();
      }
      if( getScene() != null ) {
         stage.setScene(getScene());
      }
      else {
         if( getFxmlLoaderProxy() != null && getFxmlLoaderProxy().getLocation() != null ) {
            stage.setScene(getFxmlLoaderProxy().getScene());
         }
         else {
            throw new RuntimeException("No scene found (neither as bean nor in FXMLLoaderProxy)");
         }
      }

      if( getShowAfterConfigure() == ShowAfterConfigure.Show ) {
         stage.show();
      }
      else if( getShowAfterConfigure() == ShowAfterConfigure.ShowAndWait ) {
         stage.showAndWait();
      }
   }



   public Stage createStage() {
      Stage stage = new Stage();
      configureStage(stage);
      return stage;
   }

   public Stage createStage(StageStyle stageStyle) {
      Stage stage = new Stage(stageStyle);
      configureStage(stage);
      return stage;
   }



}
