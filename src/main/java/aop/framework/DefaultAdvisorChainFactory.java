package aop.framework;

import aop.AdvisedSupport;
import aop.Advisor;
import aop.MethodMatcher;
import aop.PointcutAdvisor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultAdvisorChainFactory implements AdvisorChainFactory{
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
        Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptorList = new ArrayList<>(advisors.length);
        Class<?> actualClass = targetClass != null ? targetClass : method.getDeclaringClass();
        for(Advisor advisor :advisors){
            if(advisor instanceof PointcutAdvisor){
                PointcutAdvisor pointcutAdvisor=(PointcutAdvisor) advisor;
                if(pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)){
                    MethodMatcher methodMatcher = pointcutAdvisor.getPointcut().getMethodMatcher();
                    boolean match;
                    match=methodMatcher.matches(method,actualClass);
                    if(match){
                        MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
                        interceptorList.add(interceptor);
                    }
                }
            }
        }
        return  interceptorList;
    }
}
