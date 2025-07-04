package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @Disabled
    void sendMail(){
        redisTemplate.opsForValue().set("email", "riya.grover24032000@gmail.com");
        Object email = redisTemplate.opsForValue().get("salary");

    }

}
