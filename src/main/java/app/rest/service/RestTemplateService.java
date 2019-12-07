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

    public <T> ResponseEntity<?> getRequest(String url, T type) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        URI uri = uriService.getUri(url);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> responseEntity = restTemplate
                .exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    type.getClass()
        );

        return responseEntity;

    }

}
