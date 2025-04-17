package com.urlshortener.shortened.repositories;

import com.urlshortener.shortened.dataclasses.UrlShort;

public interface UrlRepositoryI {
    public UrlShort saveUrl(String url);
    public UrlShort getShortUrlInfo(String shortUrl);
}
