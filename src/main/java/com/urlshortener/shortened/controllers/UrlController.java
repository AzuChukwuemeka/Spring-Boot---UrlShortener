package com.urlshortener.shortened.controllers;

import com.urlshortener.shortened.dataclasses.UrlShort;
import com.urlshortener.shortened.services.UrlService;
import com.urlshortener.shortened.utils.UrlCacheRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }
    @PostMapping("/shorten")
    public UrlShort shortenUrl(@RequestBody UrlShort urlShort){
        return urlService.generateShortUrl(urlShort);
    }
    @GetMapping("/short/{code}")
    public ResponseEntity<?> redirectToOriginalLink(@PathVariable String code){
        HttpHeaders httpHeaders = new HttpHeaders();
        String longUrl = urlService.getShortUrlInfo(code);
        httpHeaders.setLocation(URI.create("https://".concat(longUrl)));
        return new ResponseEntity<>(httpHeaders,HttpStatus.FOUND);
    }
}
