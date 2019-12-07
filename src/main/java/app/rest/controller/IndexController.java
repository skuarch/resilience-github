package app.rest.controller;

import app.rest.domain.entity.GitHubUser;
import app.rest.service.ResilienceService;
import app.rest.service.RestFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ResilienceService resilienceService;

    @Autowired
    private RestFunctionService restFunctionService;

    @GetMapping
    public GitHubUser get() {
        return resilienceService
                .performFunctionWithCircuitBreaker(
                        restFunctionService.getFunction(),
                        "skuarch"
                );
    }

}
