package beans.factory.support;

import beans.factory.config.BeanDefinition;
import junit.framework.TestCase;
import org.junit.Test;

public class DefaultListableBeanFactoryTest extends TestCase {

    @Test
    public void testCreateListableBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}

class HelloService {
    public void sayHello() {
        System.out.println("Hello World");
    }
}
