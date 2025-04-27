package beans.factory.support;

import beans.BeansException;
import core.io.Resource;
import core.io.ResourceLoader;

/**
 * 读取bean定义信息
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
