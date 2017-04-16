package org.inthemoon.springfxexamples.ex02_severalcontexts.parent;

import childcontext.ChildComponentScan;
import org.inthemoon.springfx.SpringFX;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This is a bean in parent context, which instantiates child context for JavaFX
 *
 * Created by Dims on 15.04.2017.
 */
@Component
@PropertySource("classpath:severalcontexts/application.properties")
@ChildComponentScan("org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow")
public class MainWindowService extends SpringFX {
}
