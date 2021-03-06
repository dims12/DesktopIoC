package childcontext;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * This factory is a bean, that "lives" in parent context and is able
 * to create child contexts for this parent one</p>
 *
 * Configuration for child context can be provided by {@link ChildContext} and {@link ChildComponentScan}
 * annotations, by constructor parameters and by calling {@link #register(Class[])} and {@link #registerScans(String...)}
 * methods, which are all equivalent</p>
 *
 * After configurations were set, the workhorse method {@link #createChildContext()} can be called to create child
 * context. Each call creates new child context each time.</p>
 *
 * In order to access last created child context, method {@link #getLastChildContext()} is provided</p>
 *
 * Created by Dims on 25.03.2017.
 */
public class ChildContextFactory implements ApplicationContextAware {

   // parent context
   private ApplicationContext parentApplicationContext;


   public ApplicationContext getParentApplicationContext() {
      return parentApplicationContext;
   }

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      this.parentApplicationContext = applicationContext;
   }


   private final HashSet<String> basePackages = new HashSet<>();
   private final HashSet<Class<?>> annotatedClasses = new HashSet<>();

   public void register(Class<?>... newAnnotatedClasses) {
      for(Class<?> clz : newAnnotatedClasses ) {
         registerSinglePropertyAnnotations(clz);
         annotatedClasses.add(clz);
      }
   }

   public void registerScans(String... basePackages) {
      this.basePackages.addAll(Arrays.asList(basePackages));
   }

   private void registerChildContextAnnotations() {
      for (ChildContext importAnnot : getClass().getAnnotationsByType(ChildContext.class)) {
         register(importAnnot.value());
      }
   }
   
   private void registerChildComponentScanAnnotations() {
      for (ChildComponentScan componentScaneAnnot : getClass().getAnnotationsByType(ChildComponentScan.class)) {
         registerScans(componentScaneAnnot.value());
      }
   }

   /**
    * Reads all {@link SingleProperty} annotations. It is used to read them both from
    * factory class and for registered configs
    *
    * @param clz
    */
   private void registerSinglePropertyAnnotations(Class<?> clz)  {
      for (SingleProperty singleProperty : clz.getAnnotationsByType(SingleProperty.class)) {
         setContextProperty(singleProperty.key(), singleProperty.value());
      }
   }

   /**
    * Registers {@link PropertySource} annotations, applied to factory, not to config
    */
   private void registerPropertySourceAnnotations()  {
      try {
         for (PropertySource propertySource : getClass().getAnnotationsByType(PropertySource.class)) {
            for (String location : propertySource.value()) {
               ResourcePropertySource resourcePropertySource = new ResourcePropertySource(location);
               lastChildContext.getEnvironment().getPropertySources().addLast(resourcePropertySource);
            }

         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public ChildContextFactory(Class<?>... annotatedClasses) {
      register(annotatedClasses);
      registerChildContextAnnotations();
      registerChildComponentScanAnnotations();
   }

   public ChildContextFactory(String basePackage, String... restBasePackages) {
      registerScans(basePackage);
      registerScans(restBasePackages);
      registerChildComponentScanAnnotations();
      registerChildContextAnnotations();
   }




   private AnnotationConfigApplicationContext lastChildContext;
   private final SinglePropertiesSource singlePropertiesSource = new SinglePropertiesSource();

   /**
    * Returns last created child context
    * @return
    */
   public AnnotationConfigApplicationContext getLastChildContext() {
      return lastChildContext;
   }

   /**
    * Disposes last create child context
    */
   public void disposeLastChildContext() {
      lastChildContext = null;
   }


   /**
    * Workhorse method. Creates and initializes new instance of child context</p>
    *
    * Descendens should call {@link #register} (not {@link AnnotationConfigApplicationContext#register(Class[])}
    * BEFORE call to super. This is required to process {@link SingleProperty} annotations, applied to config classes
    *
    * @return
    */
   public AnnotationConfigApplicationContext createChildContext() {
      AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
      childContext.setParent(getParentApplicationContext());

      register(ChildContextConfig.class);
      childContext.getBeanFactory().addBeanPostProcessor(new ChildContextFactoryAwarePostProcessor(this));
      registerSinglePropertyAnnotations(getClass());


      if (basePackages.size() > 0) {
         childContext.scan(basePackages.toArray(new String[0]));
      }
      if (annotatedClasses.size()>0) {
         childContext.register(annotatedClasses.toArray(new Class<?>[0]));
      }

      disposeLastChildContext();

      lastChildContext = childContext;
      lastChildContext.getEnvironment().getPropertySources().addLast(singlePropertiesSource);

      registerPropertySourceAnnotations();

      return childContext;
   }




   public void setContextProperty(String key, String value) {
      singlePropertiesSource.getSource().put( key, value );
   }

   public void clearContextProperties() {
      singlePropertiesSource.getSource().clear();
   }
}
