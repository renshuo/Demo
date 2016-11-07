package hello;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by sren on 16-11-4.
 */
@Aspect
@Component
public class AspectTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public hello.Task hello.data.TaskService.getTaskByTaskId(java.lang.String))")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        log.debug("before aspect");
        startTime.set(System.currentTimeMillis());
    }

    @After(value = "webLog()", argNames = "java.lang.String")
    public void after(Task task){
        long end = System.currentTimeMillis();
        log.info("after aspect: time="+(end-startTime.get())+"; resp=");

    }
}
