package org.inthemoon.springfxexamples.ex03_severalcontexts_condensed.mainwindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.inthemoon.springfx.SpringFXML;
import org.inthemoon.springfx.StageService;
import org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow.DialogStageService;
import org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow.DialogWindowService;
import org.inthemoon.springfxexamples.ex03_severalcontexts_condensed.dialogwindow.ContextChild02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dims on 16.04.2017.
 */
@Configuration
public class ContextChild01 {

   @Bean
   SpringFXML dialogWindowService() {
      SpringFXML ans = new SpringFXML();
      ans.setContextProperty(SpringFXML.fxmlLocationPropertyName, "classpath:dialog_window2.fxml");
      ans.register(ContextChild02.class);
      return ans;
   }

   @Bean
   Button dialogButton() {

      Button ans = new Button();

      ans.setText("Open FXML dialog");
      ans.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            AnnotationConfigApplicationContext context = dialogWindowService().createChildContext();
            context.refresh();
            StageService stageService = context.getBean(StageService.class);
            stageService.createStage();
         }
      });

      return ans;
   }

   @Bean
   StackPane root() {
      StackPane ans = new StackPane();
      ans.getChildren().add(dialogButton());
      return ans;
   }

   @Bean
   Scene scene() {
      return new Scene(root(), 800, 600);
   }





}
