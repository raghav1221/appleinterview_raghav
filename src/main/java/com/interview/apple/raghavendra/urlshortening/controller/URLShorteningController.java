package com.interview.apple.raghavendra.urlshortening.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.model.URLDTO;
import com.interview.apple.raghavendra.urlshortening.model.URLErrorResponseDTO;
import com.interview.apple.raghavendra.urlshortening.model.URLResponseDto;
import com.interview.apple.raghavendra.urlshortening.service.URLShorteningService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/shortenURL")
public class URLShorteningController
{
    @Autowired
    private URLShorteningService urlShorteningService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewShortURL(@RequestBody URLDTO urlDto,HttpServletRequest request)
    {
        URL url = urlShorteningService.generateShortURL(urlDto);

        if(url != null)
        {
            URLResponseDto urlResponseDto = new URLResponseDto();
            urlResponseDto.setOriginalUrl(url.getActualUrl());
            urlResponseDto.setExpirationDate(url.getExpirationDate());
            urlResponseDto.setShortLink("http://localhost:8080/apple/"+url.getShortUrl());
            
            
            return new ResponseEntity<URLResponseDto>(urlResponseDto, HttpStatus.OK);
        }

        URLErrorResponseDTO urlErrorResponseDto = new URLErrorResponseDTO();
        urlErrorResponseDto.setStatus("404");
        urlErrorResponseDto.setError("There was an error processing your request. please try again.");
        
        return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);

    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortURL, HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(shortURL))
        {
        	URLErrorResponseDTO urlErrorResponseDto = new URLErrorResponseDTO();
            urlErrorResponseDto.setError("Short Link not provided");
            urlErrorResponseDto.setStatus("400");
            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);
        }
        URL urlToRet = urlShorteningService.getEncodedUrl(shortURL);

        if(urlToRet == null)
        {
        	URLErrorResponseDTO urlErrorResponseDto = new URLErrorResponseDTO();
            urlErrorResponseDto.setError("Url does not exist or it might have expired!");
            urlErrorResponseDto.setStatus("400");
            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now()))
        {
        	urlShorteningService.deleteShortURL(urlToRet);
            URLErrorResponseDTO urlErrorResponseDto = new URLErrorResponseDTO();
            urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
            urlErrorResponseDto.setStatus("200");
            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDto,HttpStatus.OK);
        }

        response.sendRedirect(urlToRet.getActualUrl());
        return null;
    }
}
