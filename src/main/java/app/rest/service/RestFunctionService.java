package app.rest.service;

import app.rest.domain.entity.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class RestFunctionService {

    @Autowired
    private GitHubService gitHubService;

    public Function<String, GitHubUser> getFunction(){

        Function<String, GitHubUser> function = (userName) -> {
            return gitHubService.getGitHubUser(userName);
        };

        return function;

    }

}
