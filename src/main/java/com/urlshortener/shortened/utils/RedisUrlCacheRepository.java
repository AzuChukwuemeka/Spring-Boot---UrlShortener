package com.urlshortener.shortened.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUrlCacheRepository implements UrlCacheRepository{

    private final RedisTemplate<String,Object> redisTemplate;

    public RedisUrlCacheRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getShortUrlInfo(String shortUrl) {
        return (String) redisTemplate.opsForValue().get(shortUrl);
    }

    @Override
    public void insertUrlInfo(String long_url, String short_url) {
        redisTemplate.opsForValue().set(short_url,long_url);
    }
}
