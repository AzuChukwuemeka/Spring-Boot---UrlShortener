package com.urlshortener.shortened.utils;

public interface UrlShortenerHash {
    long decode(String str);
    String encode(long num);
}
