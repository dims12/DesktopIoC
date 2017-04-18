package childcontext;

/**
 * Use this marker interface to receive instance of {@link ChildContextFactory},
 * which have created this child context
 *
 * Created by Dims on 18.04.2017.
 */
public interface ChildContextFactoryAware {
   void setChildContextFactory(ChildContextFactory childContextFactory);
}
