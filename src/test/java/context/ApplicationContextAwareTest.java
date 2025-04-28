package context;

import Service.HelloService;
import context.support.ClassPathXmlApplicationContext;
import junit.framework.TestCase;

public class ApplicationContextAwareTest extends TestCase {

    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = context.getBean("helloService", HelloService.class);
        helloService.sayHello();
        System.out.println(helloService.getApplicationContext());
        System.out.println(helloService.getBeanFactory());
    }
}