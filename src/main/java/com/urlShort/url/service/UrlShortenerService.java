package com.urlShort.url.service;

import java.util.Map;

public interface UrlShortenerService {
    String shortenUrl(String originalUrl);
    String getOriginalUrl(String shortUrl);
    Map<String, String> getAllShortenedUrls();
}
