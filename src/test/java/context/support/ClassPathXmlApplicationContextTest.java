package context.support;

import bean.Car;
import bean.Person;
import junit.framework.TestCase;

public class ClassPathXmlApplicationContextTest extends TestCase {

    public void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
    }

    public void testInitAndDestroyMethod() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();  //或者手动关闭 applicationContext.close();
    }
}