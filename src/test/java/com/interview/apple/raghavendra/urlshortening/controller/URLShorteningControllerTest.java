package com.interview.apple.raghavendra.urlshortening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.model.URLDTO;
import com.interview.apple.raghavendra.urlshortening.service.URLShorteningService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(SpringRunner.class)
@WebMvcTest(URLShorteningController.class)
public class URLShorteningControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private URLShorteningService urlShorteningService;
	
	@Mock
	private URLDTO urldto;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void createNewShortURLTest() throws Exception{
		URL url = new URL();
		url.setActualUrl("http://google.com");
		url.setShortUrl("http://localhost:8080/1");
		when(urlShorteningService.generateShortURL(urldto)).thenReturn(url);
		URLDTO urlDto = new URLDTO();
		urlDto.setUrl("http://apple.com");
		
		//this.mockMvc.perform(get("/create/")).andDo(print()).andExpect(status().isOk());
		//this.mockMvc.perform(post("/create").content(objectMapper.writeValueAsString(urlDto))).andExpect(status().isOk());
	}
	
	@Test
	public void redirectToOriginalUrl() throws Exception{
		URL url = new URL();
		url.setActualUrl("http://google.com");
		url.setShortUrl("http://localhost:8080/1");
		when(urlShorteningService.generateShortURL(urldto)).thenReturn(url);
	}

}
