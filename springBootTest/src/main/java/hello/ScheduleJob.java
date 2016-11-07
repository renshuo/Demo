package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sren on 16-11-4.
 */
@Component
public class ScheduleJob {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        log.debug("current time: "+new Date());
    }
}
