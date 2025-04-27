package beans.factory.xml;

import bean.Person;
import beans.factory.support.DefaultListableBeanFactory;
import junit.framework.TestCase;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest extends TestCase {
    @Test
    public void testXmlFile() throws Exception{
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        Person person = (Person)defaultListableBeanFactory.getBean("person");
        System.out.println(person);
        Object car = defaultListableBeanFactory.getBean("car");
        System.out.println(car);
    }
}