package org.inthemoon.springfx.fxml;

import javafx.util.Callback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Creates controllers for Java FX<p>
 *
 * Each controller is created and registered in the context, which factory belongs to.
 * I.e. usually it is a child context of Spring FX window
 *
 * Created by Dims on 08.04.2017.
 */
public class ControllerFactory implements ApplicationContextAware, Callback<Class<?>, Object> {
   @Override
   public Object call(Class<?> klass) {
      BeanDefinition definition = new RootBeanDefinition(klass);
      applicationContext.registerBeanDefinition("controller", definition);
      //applicationContext.refresh();
      return applicationContext.getBean("controller", klass);
   }

   private AnnotationConfigApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.applicationContext = (AnnotationConfigApplicationContext) applicationContext;
   }
}
