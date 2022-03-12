package com.interview.apple.raghavendra.urlshortening.service;

import com.google.common.hash.Hashing;
import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.model.URLDTO;
import com.interview.apple.raghavendra.urlshortening.repository.URLRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class URLShorteningServiceImpl implements URLShorteningService {
	private static final Logger logger = LoggerFactory.getLogger(URLShorteningServiceImpl.class);


	@Autowired
	private URLRepository urlRepository;



	public URL generateShortURL(URLDTO urlDto) {
		//check if url already exists in DB
		
		URL checkUrlIfExists = findActaulURLIfExists(urlDto);
		
		if( checkUrlIfExists !=null) 
			return checkUrlIfExists;
		
		
		if(StringUtils.isNotEmpty(urlDto.getUrl()))
		{
			String encodedUrl = encodeUrl(urlDto.getUrl());
			URL url = new URL();
			url.setCreatedDate(LocalDateTime.now());
			url.setActualUrl(urlDto.getUrl());
			url.setShortUrl(encodedUrl);
			url.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(),url.getCreatedDate()));
			URL urlToRet = persistShortURL(url);

			if(urlToRet != null)
				return urlToRet;

			return null;
		}
		return null;
	}
	public URL persistShortURL(URL url) {
		URL urlTosave = urlRepository.save(url);
		return urlTosave;
	}
	public URL getEncodedUrl(String url) {
		URL urlToRet = urlRepository.findByShortUrl(url);
		return urlToRet;
	}
	public void deleteShortURL(URL url) {
		urlRepository.delete(url);

	}
	
	private URL findActaulURLIfExists(URLDTO urlDto) {
		URL urlToRet = urlRepository.findByActualUrl(urlDto.getUrl());
		return urlToRet;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate)
	{
		if(StringUtils.isBlank(expirationDate))
		{
			return creationDate.plusSeconds(60);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	private String encodeUrl(String url)
	{
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32()
				.hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
				.toString();
		return  encodedUrl;
	}


}
