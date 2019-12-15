package app.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestTemplateService {

    @Autowired
    private UriService uriService;

    public <T> T getRequest(String url, HttpEntity<?> entity, Class<T> type) {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate
                .exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    type
        ).getBody();

    }

}
