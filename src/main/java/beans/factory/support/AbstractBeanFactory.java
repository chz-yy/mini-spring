package beans.factory.support;

import beans.BeansException;
import beans.factory.FactoryBean;
import beans.factory.config.BeanDefinition;
import beans.factory.config.BeanPostProcessor;
import beans.factory.config.ConfigurableBeanFactory;
import core.ConversionService;
import util.StringValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<StringValueResolver>();

    private ConversionService conversionService;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
       this.beanPostProcessors.remove(beanPostProcessor);
       this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for(StringValueResolver resolver : this.embeddedValueResolvers) {
            result=resolver.resolveStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null) {
            return getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition=getBeanDefinition(name);
        Object bean=createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean, name);
    }
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object=beanInstance;
        if(beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean=(FactoryBean)beanInstance;
            try {
                if(factoryBean.isSingleton()){
                    object = this.factoryBeanObjectCache.get(beanName);
                    if(object == null) {
                        object=factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    }
                }else {
                    object=factoryBean.getObject();
                }
            }catch (Exception e) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
            }
        }
        return object;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T)getBean(name));
    }


    @Override
    public boolean containsBean(String name) {
        return containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);
}
