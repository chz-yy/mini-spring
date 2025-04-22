package beans.factory;

/**
 * 该接口用于感知所属BeanFactory
 */
public interface BeanFactoryAware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
