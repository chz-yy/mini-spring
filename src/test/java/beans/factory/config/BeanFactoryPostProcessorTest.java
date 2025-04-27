package beans.factory.config;

import bean.Car;
import bean.Person;
import beans.factory.support.DefaultListableBeanFactory;
import beans.factory.xml.XmlBeanDefinitionReader;
import common.CustomBeanFactoryPostProcessor;
import common.CustomerBeanPostProcessor;
import junit.framework.TestCase;

public class BeanFactoryPostProcessorTest extends TestCase {

    public void testBeanFactoryPostProcessor() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person)beanFactory.getBean("person");
        System.out.println(person);
    }

    public void testBeanPostProcessor() throws Exception{
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        CustomerBeanPostProcessor beanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);
        Car car = (Car)beanFactory.getBean("car");
        System.out.println(car);
    }
}