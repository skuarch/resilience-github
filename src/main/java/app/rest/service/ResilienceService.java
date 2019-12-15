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

    public <T> T performFunctionWithCircuitBreaker(Function<String, T> function, String userName, Class<T> type) {

        T returnObject;

        CircuitBreaker circuitBreaker = CircuitBreaker
                .ofDefaults("backendService");

        Retry retry = Retry
                .ofDefaults("backendService");

        Supplier<T> supplier = () -> function.apply(userName);
        Supplier<T> decoratedSupplier = Decorators.ofSupplier(supplier)
                .withRetry(retry)
                .withCircuitBreaker(circuitBreaker)
                .decorate();

        returnObject = Try
                .ofSupplier(decoratedSupplier)
                .onFailure(throwable -> {
                    throw new RuntimeException(throwable); // bad practice? <- improve this shit
                })
                .get();

        return returnObject;

    }

}
