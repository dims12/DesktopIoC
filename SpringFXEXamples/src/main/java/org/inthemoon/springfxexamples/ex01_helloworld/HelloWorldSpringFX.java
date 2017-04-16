package org.inthemoon.springfxexamples.ex01_helloworld;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.inthemoon.springfx.SpringFX;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Dims on 25.03.2017.
 */
@Configuration
@PropertySource("classpath:helloworld/application.properties")
public class HelloWorldSpringFX {

   @Bean
   Alert helloWorldPressedAlert() {
      Alert ans = new Alert(Alert.AlertType.INFORMATION, "Hello world pressed");
      ans.setTitle("Information");
      ans.setHeaderText("");
      return ans;
   }

   @Bean
   Button helloWorldButton() {
      Button ans = new Button();
      ans.setText("Say 'Hello World'");
      ans.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            helloWorldPressedAlert().showAndWait();
         }
      });
      return ans;
   }

   @Bean
   StackPane root() {
      StackPane ans = new StackPane();
      ans.getChildren().add(helloWorldButton());
      return ans;
   }

   @Bean
   Scene scene() {
      return new Scene(root(), 800, 600);
   }

   public static void main(String[] args) {

      SpringFX springFX = new SpringFX(HelloWorldSpringFX.class);
      springFX.launch(args);

   }

}
