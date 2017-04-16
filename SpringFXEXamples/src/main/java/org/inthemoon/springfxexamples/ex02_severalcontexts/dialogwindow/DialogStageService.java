package org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow;

import javafx.scene.Scene;
import org.inthemoon.springfx.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 15.04.2017.
 */
@Component
public class DialogStageService extends StageService {

   private final DialogModalityFactory dialogModalityFactory;

   @Autowired
   public DialogStageService(DialogModalityFactory dialogModalityFactory) {
      this.dialogModalityFactory = dialogModalityFactory;
      setModality(dialogModalityFactory.getModality());
   }

   @Value("${dialog.title}")
   @Override
   public void setTitle(String title) {
      super.setTitle(title);
   }

   @Autowired
   @Qualifier("dialogScene")
   @Override
   public void setScene(Scene scene) {
      super.setScene(scene);
   }
}
