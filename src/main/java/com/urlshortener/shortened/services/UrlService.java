package com.urlshortener.shortened.services;

import com.urlshortener.shortened.dataclasses.UrlShort;
import com.urlshortener.shortened.repositories.UrlRepositoryI;
import com.urlshortener.shortened.utils.UrlCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final UrlRepositoryI urlRepository;
    private final UrlCacheRepository urlCacheRepository;
    public UrlService(UrlRepositoryI urlRepositoryI, UrlCacheRepository urlCacheRepository) {
        this.urlRepository = urlRepositoryI;
        this.urlCacheRepository = urlCacheRepository;
    }
    public UrlShort generateShortUrl(UrlShort urlShort){
        UrlShort urlShort1 = urlRepository.saveUrl(urlShort.getLong_url());
        urlCacheRepository.insertUrlInfo(urlShort1.getLong_url(),urlShort1.getShort_url());
        return urlShort1;
    }

    public String getShortUrlInfo(String short_url){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        String shortUrlInfo = urlCacheRepository.getShortUrlInfo(short_url);
        if(shortUrlInfo != null){
            logger.info("Redis Cache Hit for short url");
            return shortUrlInfo;
        }
        logger.info("Redis Cache Miss for short url getting it from DB");
        return urlRepository.getShortUrlInfo(short_url).getLong_url();
    }
}
