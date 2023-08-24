package ir.dotin.reactiveprogramming.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class L07_ScenarioName {

    @Test
    public void test1() {

        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("alphabets-test");

        StepVerifier.create(just, scenarioName)
                .expectNextCount(12)
                .verifyComplete();
    }

    @Test
    public void test2() {

        Flux<String> just = Flux.just("a", "b", "c");

        StepVerifier.create(just)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }

}
