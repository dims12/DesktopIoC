package org.inthemoon.springfxexamples.ex03_severalcontexts_condensed.parent;

import org.inthemoon.springfx.SpringFX;
import org.inthemoon.springfx.SpringFXApplication;
import org.inthemoon.springfxexamples.ex03_severalcontexts_condensed.mainwindow.ContextChild01;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Dims on 16.04.2017.
 */
@Configuration
@PropertySource("classpath:severalcontexts/application.properties")
public class ContextParent {

   @Bean
   SpringFX mainWindowService() {
      SpringFX ans = new SpringFX();
      ans.register(ContextChild01.class);
      return ans;
   }

   public static void main(String[] args) {

      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextParent.class);
      SpringFXApplication mainWindowService = context.getBean("mainWindowService", SpringFXApplication.class);
      mainWindowService.launch(args);

   }
}
