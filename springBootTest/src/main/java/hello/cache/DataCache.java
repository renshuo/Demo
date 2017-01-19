package hello.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;


/**
 * Created by sren on 17-1-19.
 */
@RestController
@RequestMapping("/redis")
public class DataCache  {

    @Autowired
    RedisTemplate<String, String>  redisTemplate;


    @RequestMapping(value = "/add/{key}/{value}", method = RequestMethod.GET)
    public void addValue(@PathVariable("key") String key, @PathVariable("value") String data){
        redisTemplate.opsForValue().set(key, data);
    }

    @RequestMapping(value= "/get/{key}", method = RequestMethod.GET)
    public String get(@PathVariable("key") String key){
        return redisTemplate.opsForValue().get(key);
    }

}
