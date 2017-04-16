package org.inthemoon.springfxexamples.ex02_severalcontexts.parent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This is a class with {@link #main(String[])} method.</p>
 *
 * It is made separate, although could be merged with any other class like {@link MainWindowService}
 *
 * Created by Dims on 15.04.2017.
 */
public class StarterAppMain {

   public static void main(String[] args) {

      // parent context is configuren by scanning package
      // it can be configured any other way, but should contain {@link SpringFX} bean
      AnnotationConfigApplicationContext context =
         new AnnotationConfigApplicationContext("org.inthemoon.springfxexamples.ex02_severalcontexts.parent");

      // here SpringFX class is extended to MainWindowService and namely it is launched
      MainWindowService mainWindowService = context.getBean(MainWindowService.class);
      mainWindowService.launch(args);

   }

}
