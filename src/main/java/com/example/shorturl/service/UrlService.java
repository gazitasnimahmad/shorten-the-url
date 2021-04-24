package com.example.shorturl.service;

import com.example.shorturl.dto.UrlDto;
import com.example.shorturl.model.Url;
import com.example.shorturl.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class UrlService {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;

    @Autowired
    private UrlRepo urlRepo;

    public UrlService(){}

    public String convertToShortUrl(UrlDto urlDto) {
        String urlString = urlDto.getUrlName();
        var url = new Url();
        url.setUrlName(urlString);
        var entity = urlRepo.save(url);

        var encodedString = new StringBuilder();
        long input = entity.getUrlId();

        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }
        return encodedString.reverse().toString();
    }

    public String getOriginalUrl(String shortUrl) {
        String input = shortUrl;
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        //counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }

        var entity = urlRepo.findById((long) decoded)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        return entity.getUrlName();
    }


}
