package beans.factory;

import bean.Car;
import context.support.ClassPathXmlApplicationContext;
import context.support.ClassPathXmlApplicationContextTest;
import junit.framework.TestCase;

public class FactoryBeanTest extends TestCase {
    public void testFactoryBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = context.getBean("car", Car.class);
        context.getBean("car");
        System.out.println(car.getColor());
    }
}
