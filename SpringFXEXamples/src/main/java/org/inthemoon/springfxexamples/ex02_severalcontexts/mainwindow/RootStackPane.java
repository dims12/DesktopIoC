package org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow;

import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 16.04.2017.
 */
@Component
public class RootStackPane extends StackPane {


   private final DialogButton dialogButton;


   @Autowired
   public RootStackPane(DialogButton dialogButton) {
      this.dialogButton = dialogButton;

      getChildren().add(dialogButton);
   }
}
