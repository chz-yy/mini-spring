package context.annotation;

import beans.factory.config.BeanDefinition;
import beans.factory.support.BeanDefinitionRegistry;
import cn.hutool.core.util.StrUtil;
import stereotype.Component;
import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{
    public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "context.annotation.internalAutowiredAnnotationProcessor";
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for(String basePackage : basePackages){
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for(BeanDefinition candidateComponent : candidateComponents){
                String beanScope = resolveBeanScope(candidateComponent);
                if(StrUtil.isNotEmpty(beanScope)){
                    candidateComponent.setScope(beanScope);
                }
                String beanName = determineBeanName(candidateComponent);
                registry.registerBeanDefinition(beanName, candidateComponent);
            }
        }
        //注册处理@Autowired和@Value注解的BeanPostProcessor
        //还没写 chz
    }

    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            value=StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }


    /**
     * 获取bean的作用域
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if(scope != null){
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
}
