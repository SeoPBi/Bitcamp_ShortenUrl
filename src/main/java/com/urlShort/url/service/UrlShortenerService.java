package com.urlShort.url.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UrlShortenerService {

    private final Map<String, String> urlMap = new HashMap<>();
    private final String BASE_URL = "https://example.com/"; // 단축 URL의 기본 도메인

    public String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        urlMap.put(shortUrl, originalUrl);
        return BASE_URL + shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    private String generateShortUrl() {
        // 단축 URL 생성 로직 구현 (예시로 랜덤 문자열 생성)
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public Map<String, String> getAllShortenedUrls() {
        synchronized (urlMap) {
            return new HashMap<>(urlMap);
        }
    }

}
