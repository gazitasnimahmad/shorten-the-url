package com.example.shorturl.controller;

import com.example.shorturl.dto.UrlDto;
import com.example.shorturl.model.Url;
import com.example.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService= urlService;
    }


    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody UrlDto urlDto) {
        return urlService.convertToShortUrl(urlDto);
    }

    @GetMapping("/getUrls/{shortUrl}")
    public ResponseEntity<?> getAndRedirect(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        System.out.println(url+"this is the new one");
        return new ResponseEntity<String>(url,HttpStatus.OK);

    }

}
