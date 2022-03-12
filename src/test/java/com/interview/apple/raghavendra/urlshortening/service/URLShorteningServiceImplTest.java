package com.interview.apple.raghavendra.urlshortening.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.model.URLDTO;
import com.interview.apple.raghavendra.urlshortening.repository.URLRepository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class URLShorteningServiceImplTest {

	
	@InjectMocks
	private URLShorteningServiceImpl urlShorteningServiceImpl;
	
	
	private URL url;
	
	@Mock
	private URLRepository urlRepository;
	
	private URLDTO urldto;
	
	@Before
	public void setup() {
		
		urldto = new URLDTO();
		urldto.setUrl("http://xyz.com");
		url = new URL();
		url.setActualUrl("http://abc.com");
	}
	
	
	@Test
    public void generateShortURL() {

		when(urlRepository.findByActualUrl(urldto.getUrl())).thenReturn(url);
    	when(urlRepository.save(url)).thenReturn(url);
    	URL urltoret = urlShorteningServiceImpl.generateShortURL(urldto);
    	assertNotNull(urltoret);
    	
    }
	
	@Test
    public void persistShortURL() {
    	when(urlRepository.save(url)).thenReturn(url);
    	URL urltoRet = urlShorteningServiceImpl.persistShortURL(url);
    	assertNotNull(urltoRet);
    }
	
	@Test
    public void getEncodedUrl() {
    	when(urlRepository.findByShortUrl(urldto.getUrl())).thenReturn(url);
    	URL urltoRet =urlShorteningServiceImpl.getEncodedUrl(urldto.getUrl());
    	assertNotNull(urltoRet);
    }
    
	
	
}
