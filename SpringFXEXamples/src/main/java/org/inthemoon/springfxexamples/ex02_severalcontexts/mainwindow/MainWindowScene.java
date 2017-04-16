package org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow;

import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 16.04.2017.
 */
@Component
public class MainWindowScene extends Scene {

   @Autowired
   public MainWindowScene(RootStackPane rootStackPane) {
      super(rootStackPane, 800, 600);
   }
}
