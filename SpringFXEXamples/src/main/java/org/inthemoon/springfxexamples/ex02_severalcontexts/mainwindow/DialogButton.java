package org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow.DialogStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 16.04.2017.
 */
@Component
public class DialogButton extends Button {

   private final DialogWindowService dialogWindowService;

   @Autowired
   public DialogButton(DialogWindowService dialogWindowService) {
      this.dialogWindowService = dialogWindowService;

      setText("Open FXML dialog");
      setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            AnnotationConfigApplicationContext context = dialogWindowService.createChildContext();
            context.refresh();
            DialogStageService stageService = context.getBean("dialogStageService", DialogStageService.class);
            stageService.createStage();
         }
      });
   }
}
