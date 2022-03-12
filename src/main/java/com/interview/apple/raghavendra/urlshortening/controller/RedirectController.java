package com.interview.apple.raghavendra.urlshortening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.interview.apple.raghavendra.urlshortening.model.URL;
import com.interview.apple.raghavendra.urlshortening.service.URLShorteningService;

@Controller
public class RedirectController {

	@Autowired
	private URLShorteningService urlShorteningService;

	@GetMapping("/apple/{shortURL}")
	public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes,@PathVariable String shortURL) {
		//attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
		//attributes.addAttribute("attribute", "redirectWithRedirectView");
		URL url = urlShorteningService.getEncodedUrl(shortURL);
		
		return new RedirectView(url.getActualUrl());
	}

}
