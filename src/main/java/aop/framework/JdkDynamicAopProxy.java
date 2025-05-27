package aop.framework;

import aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(),advised.getTargetSource().getTargetClass(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object target = advised.getTargetSource().getTarget();
        Class<?> targetClass = target.getClass();
        Object retVal=null;
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
        if(chain==null||chain.isEmpty()){
            return method.invoke(target,args);
        }else {
            MethodInvocation invocation =
                    new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
            retVal=invocation.proceed();
        }
        return retVal;
    }
}
