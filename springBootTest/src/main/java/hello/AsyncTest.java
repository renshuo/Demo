package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Future;

/**
 * Created by sren on 16-11-4.
 */
@Controller
@RequestMapping("/async")
public class AsyncTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Async
    public AsyncResult<String> firstFun() throws InterruptedException {
        Thread.sleep(10000);
        log.info("first func, ");
        return new AsyncResult<>("success 1");
    }

    @RequestMapping("/")
    @ResponseBody
    public String testAsync() throws InterruptedException {
        log.info("start test.");
        Future<String> x1 = firstFun();
        while(!x1.isDone()){
            log.info("wait for firust fun.");
            Thread.sleep(1000);
        }

        log.info("all finished.");
        return "success.";
    }
}
