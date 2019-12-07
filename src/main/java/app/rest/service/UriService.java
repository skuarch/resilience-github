package app.rest.service;

import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UriService {

    public URI getUri(String url) {

        if(url == null || url.isEmpty()) {
            throw new IllegalArgumentException("url is null or empty");
        }

        URI uri = null;

        try{
            uri = new URI(url);
        }catch(URISyntaxException e){
            e.printStackTrace();
        }

        return uri;
    }

}
