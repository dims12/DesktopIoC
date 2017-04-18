package org.inthemoon.springfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;

/**
 * This is stage configurer. This bean has {@link #setStage(Stage)}
 * method, on which it configures assigned stage, taking required beans
 * and parameters from context</p>
 *
 * This bean is used to configure {@code primaryStage}, but it also can be used
 * to configure any stages in child contexts.</p>
 *
 * Finaly it can be use to create and configure new stage at once
 *
 * Created by Dims on 25.03.2017.
 */
public class StageService implements ApplicationContextAware, InitializingBean {

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



   private FXMLLoader fxmlLoader;

   public FXMLLoader getFxmlLoader() {
      return fxmlLoader;
   }

   @Autowired(required = false)
   public void setFxmlLoader(FXMLLoader fxmlLoader) {
      this.fxmlLoader = fxmlLoader;
   }





   private String title;

   public String getTitle() {
      return title;
   }

   @Value("${primaryStage.title}")
   public void setTitle(String title) {
      this.title = title;
   }




   @Override
   public void afterPropertiesSet() throws Exception {
      if( getScene() == null && getFxmlLoader() == null ) {
         throw new IllegalStateException("Either 'scene' or 'fxmlLoader' should be set");
      }
   }






   private Stage stage;

   public Stage getStage() {
      return stage;
   }


   protected void configureStage()  {

      if( stage == null ) {
         throw new NullPointerException();
      }

      if( getModality() != null ) {
         stage.initModality(getModality());
      }

      if( getTitle() != null ) {
         stage.setTitle(title);
      }


      try {
         if (getScene() != null) {
            stage.setScene(getScene());
         } else if (getFxmlLoader() != null) {
            stage.setScene(getFxmlLoader().load());
         }
         else {
            throw new AssertionError();
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }

      if( getShowAfterConfigure() == ShowAfterConfigure.Show ) {
         stage.show();
      }
      else if( getShowAfterConfigure() == ShowAfterConfigure.ShowAndWait ) {
         stage.showAndWait();
      }

   }

   public void setStage(Stage stage) {
      this.stage = stage;
      configureStage();
   }












   public Stage createStage() {
      Stage stage = new Stage();
      setStage(stage);
      return stage;
   }

   public Stage createStage(StageStyle stageStyle) {
      Stage stage = new Stage(stageStyle);
      setStage(stage);
      return stage;
   }



}
