package childcontext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Looks for beans of class {@link ChildContextFactoryAware} and assigns
 * {@link #childContextFactory} to them
 *
 * Created by Dims on 18.04.2017.
 */
public class ChildContextFactoryAwarePostProcessor implements BeanPostProcessor {

   private final ChildContextFactory childContextFactory;

   public ChildContextFactoryAwarePostProcessor(ChildContextFactory childContextFactory) {
      this.childContextFactory = childContextFactory;
   }


   @Override
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      if( bean instanceof ChildContextFactoryAware ) {
         ((ChildContextFactoryAware) bean).setChildContextFactory(childContextFactory);
      }
      return bean;
   }

   @Override
   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
      return bean;
   }


}
