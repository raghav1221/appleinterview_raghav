package com.interview.apple.raghavendra.urlshortening.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.apple.raghavendra.urlshortening.model.URL;

@Repository
public interface URLRepository extends JpaRepository<URL,Long>
{
    public URL findByShortUrl(String shortLink);
    public URL findByActualUrl(String actualUrl);
    
}
