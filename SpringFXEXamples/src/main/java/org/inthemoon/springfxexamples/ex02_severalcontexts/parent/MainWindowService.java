package org.inthemoon.springfxexamples.ex02_severalcontexts.parent;

import childcontext.ChildComponentScan;
import org.inthemoon.springfx.SpringFX;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This bean resides in parent context, but knows all configuration of child context, it can create</p>
 *
 * This is information is provided by:</p>
 *
 * 1) by base class of {@link SpringFX}</p>
 *
 * 2) annotation {@link PropertySource} provides information about property file</p>
 *
 * 3) annotation of {@link ChildComponentScan} provides information about child context. Here we define
 * child context by component scan. It is scanned only on child context creation with {@link #createChildContext()}
 *
 * Created by Dims on 15.04.2017.
 */
@Component
@PropertySource("classpath:severalcontexts/application.properties")
@ChildComponentScan("org.inthemoon.springfxexamples.ex02_severalcontexts.mainwindow")
public class MainWindowService extends SpringFX {
}
