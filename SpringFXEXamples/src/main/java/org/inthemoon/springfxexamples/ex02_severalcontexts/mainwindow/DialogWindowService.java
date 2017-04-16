package org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow;

import childcontext.ChildComponentScan;
import childcontext.SingleProperty;
import org.inthemoon.springfx.SpringFXML;
import org.springframework.stereotype.Component;

/**
 * Created by Dims on 15.04.2017.
 */
@Component
@ChildComponentScan("org.inthemoon.springfxexamples.ex02_severalcontexts.dialogwindow")
@SingleProperty(key = SpringFXML.fxmlLocationPropertyName, value = "classpath:dialog_window.fxml")
public class DialogWindowService extends SpringFXML {
}
