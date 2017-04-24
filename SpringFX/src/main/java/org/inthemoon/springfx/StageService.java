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
 * This is lastStage configurer. This bean has {@link #configureStage(Stage)}
 * method, on which it configures assigned lastStage, taking required beans
 * and parameters from context</p>
 *
 * This bean is used to configure {@code primaryStage}, but it also can be used
 * to configure any stages in child contexts.</p>
 *
 * Finaly it can be use to create and configure new lastStage at once
 *
 * Created by Dims on 25.03.2017.
 */
public class StageService implements ApplicationContextAware {

   private ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
   }

   public ApplicationContext getApplicationContext() {
      return applicationContext;
   }



   private ShowAfterConfigure showAfterConfigure = ShowAfterConfigure.Show;

   public ShowAfterConfigure getShowAfterConfigure() {
      return showAfterConfigure;
   }

   public void setShowAfterConfigure(ShowAfterConfigure showAfterConfigure) {
      this.showAfterConfigure = showAfterConfigure;
   }


   private SceneMode sceneMode = SceneMode.SceneBean;

   public SceneMode getSceneMode() {
      return sceneMode;
   }

   public void setSceneMode(SceneMode sceneMode) {
      this.sceneMode = sceneMode;
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

   @Value("${stage.title}")
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


      if( getSceneMode() == SceneMode.SceneBean ) {
         if( getScene() == null ) {
            throw new NullPointerException();
         }
         stage.setScene(getScene());
      }
      else if( getSceneMode() == SceneMode.FXMLLoaderBean ) {
         if( getFxmlLoaderProxy() == null ) {
            throw new NullPointerException();
         }
         stage.setScene(getFxmlLoaderProxy().getScene());
      }
      else {
         throw new AssertionError();
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
