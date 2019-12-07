package app.rest.service;

import app.rest.domain.entity.GitHubUser;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class ResilienceService {

    public GitHubUser performFunctionWithCircuitBreaker(Function<String, GitHubUser> function, String userName) {

        CircuitBreaker circuitBreaker = CircuitBreaker
                .ofDefaults("backendService");

        Retry retry = Retry
                .ofDefaults("backendService");

        Supplier<GitHubUser> supplier = () -> function.apply(userName);
        Supplier<GitHubUser> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withRetry(retry)
                .withCircuitBreaker(circuitBreaker)
                .decorate();

        GitHubUser result = Try
                .ofSupplier(decoratedSupplier)
                .onFailure(throwable -> {
                    throw new RuntimeException(throwable); // bad practice? <- improve this shit
                })
                .get();

        return result;

    }

}
