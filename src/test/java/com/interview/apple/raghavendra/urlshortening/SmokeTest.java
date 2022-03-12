package com.interview.apple.raghavendra.urlshortening;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.apple.raghavendra.urlshortening.controller.RedirectController;
import com.interview.apple.raghavendra.urlshortening.controller.URLShorteningController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private URLShorteningController urlShorteningController;
	
	@Autowired
	private RedirectController redirectController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(urlShorteningController).isNotNull();
		assertThat(redirectController).isNotNull();
		
	}
}