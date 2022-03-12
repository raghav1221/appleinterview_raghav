package com.interview.apple.raghavendra.urlshortening.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class URL {
	
	@Id
    @GeneratedValue
    private long id;
    @Lob
    private String actualUrl;
    private String shortUrl;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    public URL(long id, String actualUrl, String shortUrl, LocalDateTime createdDate, LocalDateTime expirationDate) {
        this.id = id;
        this.actualUrl = actualUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
    }
    
    public URL() {
    	
    }


}
