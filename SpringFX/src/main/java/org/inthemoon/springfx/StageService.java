package org.inthemoon.springfx;

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
public class StageService implements ApplicationContextAware {

   private ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = applicationContext;
   }

   public ApplicationContext getApplicationContext() {
      return applicationContext;
   }



   private boolean showAfterConfigure = true;

   public boolean isShowAfterConfigure() {
      return showAfterConfigure;
   }

   public void setShowAfterConfigure(boolean showAfterConfigure) {
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

   @Autowired
   public void setScene(Scene scene) {
      this.scene = scene;
   }





   private String title;

   public String getTitle() {
      return title;
   }

   @Value("${primaryStage.title}")
   public void setTitle(String title) {
      this.title = title;
   }





   private Stage stage;

   public Stage getStage() {
      return stage;
   }


   protected void configureStage() {

      if( stage == null ) {
         throw new NullPointerException();
      }

      if( getModality() != null ) {
         stage.initModality(getModality());
      }

      if( getTitle() != null ) {
         stage.setTitle(title);
      }


      if( getScene() != null ) {
         stage.setScene(getScene());
      }
      else {
         throw new RuntimeException("No scene configured");
      }

      if( isShowAfterConfigure() ) {
         stage.show();
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
