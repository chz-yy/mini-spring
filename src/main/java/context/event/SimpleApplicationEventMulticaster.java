package context.event;

import beans.BeansException;
import beans.factory.BeanFactory;
import context.ApplicationEvent;
import context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * 遍历所有监听器，判断监听器是否对传入的事件感兴趣，是则执行监听器的方法
     * @param event
     */
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for(ApplicationListener<ApplicationEvent> applicationListener:applicationListeners){
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    /**
     * 监听器是否对该事件感兴趣
     *
     * @param applicationListener
     * @param event
     * @return
     */
    private boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        //通过反射获取监听器类实现的第一个泛型接口（通常是 ApplicationListener<EventType>）
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        //提取接口中泛型参数的实际类型（即 ApplicationListener<...> 尖括号中的具体事件类型）
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        //将泛型类型转换为全限定类名字符串（例如 com.example.OrderCreatedEvent），用于后续加载类。
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            //据类名字符串动态加载对应的 Class 对象。如果类不存在，抛出异常（例如监听的类配置错误）。
            eventClass= Class.forName(className);
        }catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        //判断当前事件对象 (event) 是否是 eventClass 的子类或实现类。
        return eventClass.isAssignableFrom(event.getClass());
    }
}
