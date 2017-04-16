package org.inthemoon.springfx.fx;

import org.inthemoon.springfx.StageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dims on 08.04.2017.
 */
@Configuration
public class FXConfig {

   @Bean
   StageService primaryStageConfigurer() {
      return new StageService();
   }
}
