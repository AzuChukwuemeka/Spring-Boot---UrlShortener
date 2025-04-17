package com.urlshortener.shortened.utils;

public interface UrlCacheRepository {
    String getShortUrlInfo(String shortUrl);

    void insertUrlInfo(String long_url, String short_url);
}
