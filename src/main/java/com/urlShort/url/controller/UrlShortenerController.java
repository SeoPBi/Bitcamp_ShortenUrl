package com.urlShort.url.controller;

import com.urlShort.url.service.UrlShortenerService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam("url") String originalUrl) {
        String shortenedUrl = urlShortenerService.shortenUrl(originalUrl);
        return ResponseEntity.ok(shortenedUrl);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, originalUrl)
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        Map<String, String> shortenedUrls = urlShortenerService.getAllShortenedUrls();
        if (shortenedUrls == null) {
            shortenedUrls = new HashMap<>();
        }
        model.addAttribute("shortenedUrls", shortenedUrls);
        return "index";
    }
}
