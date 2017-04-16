package org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Dims on 16.04.2017.
 */
@Component
public class DialogScene extends Scene {

   private final FXMLLoader fxmlLoader;

   @Autowired
   public DialogScene(FXMLLoader fxmlLoader) throws IOException {
      super(fxmlLoader.load());
      this.fxmlLoader = fxmlLoader;
   }
}
