package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
