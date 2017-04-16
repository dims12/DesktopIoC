package org.inthemoon.springfxexamples.ex02_severalcontexts.parent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This is a class with {@link #main(String[])} method.</p>
 *
 * It can be merged into
 *
 *
 * Created by Dims on 15.04.2017.
 */
public class StarterAppMain {

   public static void main(String[] args) {

      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.inthemoon.springfxexamples.ex02_severalcontexts.parent");
      MainWindowService mainWindowService = context.getBean(MainWindowService.class);
      mainWindowService.launch(args);

   }

}
