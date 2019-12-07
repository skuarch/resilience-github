package app.rest.service;

import app.rest.domain.entity.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GitHubService {

    @Autowired
    private RestTemplateService restTemplateService;

    public GitHubUser getGitHubUser(String userName) {

        GitHubUser user;
        Optional<GitHubUser> optional = null;

        ResponseEntity<?> responseEntity = restTemplateService
                .getRequest("https://api.githu.com/users/" + userName, new GitHubUser());

        if(responseEntity == null) {
            user = new GitHubUser();
        } else {
            user = GitHubUser.class.cast(responseEntity.getBody());
        }
        return user;

    }

}
