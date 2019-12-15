package app.rest.service;

import app.rest.domain.entity.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class GitHubService {

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private UriService uriService;

    public GitHubUser getGitHubUser(String userName) {

        GitHubUser user;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://api.github.com/users/" + userName;
        URI uri = uriService.getUri(url);

        user = restTemplateService
                .getRequest(
                        uri.toString(),
                        entity,
                        GitHubUser.class
                );

        if(user == null) {
            user = new GitHubUser();
        }

        return user;

    }

}
