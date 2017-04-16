package org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is normal Java FX FXML controller</p>
 *
 * Simultaneously, it is a subject of DI: we see, that bean {@link #buttonPressedAlert} is injected from child context
 *
 * Created by Dims on 15.04.2017.
 */
public class DialogWindowController {

   private final ButtonPressedAlert buttonPressedAlert;

   @Autowired
   public DialogWindowController(ButtonPressedAlert buttonPressedAlert) {
      this.buttonPressedAlert = buttonPressedAlert;
   }

   @FXML
   Button button;


   public void buttonPressed(ActionEvent actionEvent) {
      buttonPressedAlert.showAndWait();
   }
}
