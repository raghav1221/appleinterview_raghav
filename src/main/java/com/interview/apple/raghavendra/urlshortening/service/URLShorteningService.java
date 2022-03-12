package com.interview.apple.raghavendra.urlshortening.service;



import org.springframework.stereotype.Service;

import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.model.URLDTO;

@Service
public interface URLShorteningService
{
    public URL generateShortURL(URLDTO urlDto);
    public URL persistShortURL(URL url);
    public URL getEncodedUrl(String url);
    public  void  deleteShortURL(URL url);
}
