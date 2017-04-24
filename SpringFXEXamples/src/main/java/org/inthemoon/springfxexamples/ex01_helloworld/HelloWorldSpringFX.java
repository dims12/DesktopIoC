package org.inthemoon.springfxexamples.ex01_helloworld;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.inthemoon.springfx.SpringFX;
import org.inthemoon.springfx.SpringFXApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Entire application is inside single Spring configuration. This configuration is
 * used to create context in {@link #main(String[])} method, then application is ran
 * by {@link SpringFX#launch(String[])} method.</p>
 *
 * All components are in the form of Spring beans.
 *
 * Created by Dims on 25.03.2017.
 */
@Configuration
@PropertySource("classpath:helloworld/application.properties")
public class HelloWorldSpringFX {

   /**
    * Alert window
    * @return
    */
   @Bean
   Alert helloWorldPressedAlert() {
      Alert ans = new Alert(Alert.AlertType.INFORMATION, "Hello world pressed");
      ans.setTitle("Information");
      ans.setHeaderText("");
      return ans;
   }

   /**
    * A button to invoke alert window
    * @return
    */
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

   /**
    * Root pane to hold button
    * @return
    */
   @Bean
   StackPane root() {
      StackPane ans = new StackPane();
      ans.getChildren().add(helloWorldButton());
      return ans;
   }

   /**
    * Scene
    * @return
    */
   @Bean
   Scene scene() {
      return new Scene(root(), 800, 600);
   }

   /**
    * Starter code
    * @param args
    */
   public static void main(String[] args) {

      // get SpringFX class
      // this class behaves like Spring context factory and receives annotated classes as argument
      // besides beans from this configuration, it adds another service beans, which rund Java FX
      SpringFXApplication springFX = new SpringFXApplication(HelloWorldSpringFX.class);

      // launching
      springFX.launch(args);

   }

}
