package context.support;

import beans.BeansException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private  String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[] {configLocation});
    }

    /**
     * 从xml文件中加载BeanDefinition 并自动刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
