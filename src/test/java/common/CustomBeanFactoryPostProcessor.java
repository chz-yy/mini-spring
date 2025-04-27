package common;

import beans.BeansException;
import beans.PropertyValue;
import beans.PropertyValues;
import beans.factory.ConfigurableListableBeanFactory;
import beans.factory.config.BeanDefinition;
import beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor#postProcessBeanFactory");
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = person.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","chz2"));
    }
}
