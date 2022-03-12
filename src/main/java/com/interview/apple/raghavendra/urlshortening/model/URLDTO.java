package com.interview.apple.raghavendra.urlshortening.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class URLDTO {

	private String url;
	private String expirationDate;  //optional

	public URLDTO(String url, String expirationDate) {
		this.url = url;
		this.expirationDate = expirationDate;
	}

	public URLDTO() {
	}


}
