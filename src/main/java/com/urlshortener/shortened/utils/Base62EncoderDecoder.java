package com.urlshortener.shortened.utils;

import org.springframework.stereotype.Component;

@Component
public class Base62EncoderDecoder implements UrlShortenerHash{
    private final String ALPHABET;
    private final int BASE;
    public Base62EncoderDecoder() {
        this.ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        this.BASE = ALPHABET.length();
    }

    // Encodes a number to Base62 string
    public String encode(long num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET.charAt((int)(num % BASE)));
            num /= BASE;
        }
        return sb.reverse().toString();
    }
    public long decode(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = result * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return result;
    }
}