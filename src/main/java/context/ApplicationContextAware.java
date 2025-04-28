package context;

import beans.BeansException;
import beans.factory.Aware;
/**
 * 实现该接口，能感知所属ApplicationContext
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
