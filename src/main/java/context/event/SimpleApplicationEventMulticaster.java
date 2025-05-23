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
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass= Class.forName(className);
        }catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClass.isAssignableFrom(event.getClass());
    }
}
