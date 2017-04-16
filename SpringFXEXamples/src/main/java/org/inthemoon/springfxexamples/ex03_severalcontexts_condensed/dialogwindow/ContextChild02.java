package org.inthemoon.springfxexamples.ex03_severalcontexts_condensed.dialogwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import org.inthemoon.springfx.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by Dims on 16.04.2017.
 */
@Configuration
public class ContextChild02 {

   @Bean
   Alert buttonPressedAlert() {
      Alert ans = new Alert(Alert.AlertType.INFORMATION);
      ans.setTitle("Information Dialog");
      ans.setHeaderText(null);
      ans.setContentText("Button was pressed");
      return ans;
   }

   @Bean
   Modality getModality() {
      return Modality.APPLICATION_MODAL;
   }

   @Bean
   Scene scene(@Autowired  FXMLLoader fxmlLoader) throws IOException {
      Scene ans = new Scene(fxmlLoader.load());
      return ans;
   }

   @Bean
   StageService stageService(@Autowired  ApplicationContext context) {
      StageService ans = new StageService();
      ans.setTitle( context.getEnvironment().getProperty("dialog.title") );
      return ans;
   }
}
