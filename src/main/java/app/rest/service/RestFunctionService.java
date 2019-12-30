package app.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class RestFunctionService {

    @Autowired
    private GitHubService gitHubService;

    public <T> Function<String, T> getFunction(Class<T> type){

        Function<String, T> function = (userName) -> {
            return type.cast(
                    gitHubService.getGitHubUser(userName)
            );
        };

        return function;

    }

}
