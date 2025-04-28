package beans.factory;

import beans.BeansException;

/**
 * 该接口用于感知所属BeanFactory
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
