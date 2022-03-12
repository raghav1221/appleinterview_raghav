package com.interview.apple.raghavendra.urlshortening.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class URLErrorResponseDTO
{
    private String status;
    private String error;

    public URLErrorResponseDTO(String status, String error) {
        this.status = status;
        this.error = error;
    }
    
    public URLErrorResponseDTO() {
    	
    }

}
