package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T get(String key, Class<T> entityClass) throws JsonProcessingException {
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) return null;
        return mapper.readValue(o.toString(), entityClass);
    }

    public void set(String key, Object o, Long ttl) {
        try {
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Error setting key in Redis: " + e.getMessage(), e);
        }
    }
}
