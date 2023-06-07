package com.urlShort.url.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final Map<String, String> shortenedUrls;
    private final Random random;

    public UrlShortenerServiceImpl() {
        this.shortenedUrls = new HashMap<>();
        this.random = new Random();
    }

    @Override
    public String shortenUrl(String originalUrl) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 8;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        String shortenedUrl = sb.toString();
        shortenedUrls.put(shortenedUrl, originalUrl);

        return shortenedUrl;
    }



    @Override
    public String getOriginalUrl(String shortUrl) {
        return shortenedUrls.get(shortUrl);
    }

    @Override
    public Map<String, String> getAllShortenedUrls() {
        return shortenedUrls;
    }
}
