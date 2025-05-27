package aop;

import Service.WorldService;
import Service.WorldServiceImpl;

import aop.aspectj.AspectJExpressionPointcutAdvisor;
import aop.framework.ProxyFactory;
import org.junit.Before;
import org.junit.Test;

public class DynamicProxyTest {
    private AdvisedSupport advisedSupport;
    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();
        advisedSupport = new ProxyFactory();
        //Advisor是Pointcut和Advice的组合
        String expression = "execution(* org.springframework.test.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        AfterReturningAdviceInterceptor methodInterceptor = new AfterReturningAdviceInterceptor(new WorldServiceAfterReturnAdvice());
        advisor.setAdvice(methodInterceptor);
        TargetSource targetSource = new TargetSource(worldService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.addAdvisor(advisor);
    }
}
