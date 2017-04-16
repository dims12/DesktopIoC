package org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow;

import javafx.stage.Modality;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 16.04.2017.
 */
@Component
public class DialogModalityFactory {

   public Modality getModality() {
      return Modality.APPLICATION_MODAL;
   }
}
