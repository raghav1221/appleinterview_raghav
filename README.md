->haven the uniquecode maps to actualurl
->In database we need to create we need to create, ex= longurl, shorturl,createdDate and expirationDate(optianal)
->generate unique code, best way is to take to hashing.murmur by passing the timestamp
->if longURL already exists, return the existing shortcode
->create the end points createNewShortURL,redirectToOriginalUrl

Explanation 
->Start the application server using by running app.java 
->enter the url in the browser http://localhost:8080
->Enter the Actual URL in the inputbox provide and click on submit
->Observe the shorl URL being generated below.
-copy the short url to browser, observe it is being redirected to actual URL