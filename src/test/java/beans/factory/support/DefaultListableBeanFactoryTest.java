package beans.factory.support;

import beans.PropertyValue;
import beans.PropertyValues;
import beans.factory.config.BeanDefinition;
import beans.factory.config.BeanReference;
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

    @Test
    public void testPopulateBeanWithPropertyValues () {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","张三"));
        propertyValues.addPropertyValue(new PropertyValue("age",20));
        propertyValues.addPropertyValue(new PropertyValue("love","yy"));
        BeanDefinition beanDefinition=new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinition("person",beanDefinition);
        Person person=(Person) beanFactory.getBean("person");
        person.info();

    }
    @Test
    public void testBeanReference () {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("color","蓝"));
        propertyValues.addPropertyValue(new PropertyValue("engine","小米"));
        BeanDefinition beanDefinitionCar=new BeanDefinition(Car.class,propertyValues);

        propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","张三"));
        propertyValues.addPropertyValue(new PropertyValue("age",20));
        propertyValues.addPropertyValue(new PropertyValue("love","yy"));
        propertyValues.addPropertyValue(new PropertyValue("car",new BeanReference("car")));
        BeanDefinition beanDefinitionPerson=new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinition("person",beanDefinitionPerson);
        beanFactory.registerBeanDefinition("car",beanDefinitionCar);
        Person person=(Person) beanFactory.getBean("person");
        person.info();

    }
}

class Person {
    private String name;
    private int age;
    private String love;
    private Car car;
    public void info() {
        System.out.println(name+" "+age+" "+love+" "+car.getColor()+" "+car.getEngine());
    }
}

class Car {
    private String color;
    private String engine;
    public String getColor() {
        return color;
    }
    public String getEngine() {
        return engine;
    }
}

class HelloService {
    public void sayHello() {
        System.out.println("Hello World");
    }
}
