package context.event;

import beans.BeansException;
import beans.factory.BeanFactory;
import beans.factory.BeanFactoryAware;
import context.ApplicationEvent;
import context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster , BeanFactoryAware {
    private BeanFactory beanFactory;
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

}
