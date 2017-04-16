package org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

/**
 * This is normal JavaFX Alert class, but it is a {@link Component} and injected into controller
 *
 * Created by Dims on 15.04.2017.
 */
@Component
public class ButtonPressedAlert extends Alert{
   public ButtonPressedAlert() {
      super(AlertType.INFORMATION);
      setTitle("Information Dialog");
      setHeaderText(null);
      setContentText("Button was pressed");
   }
}
