package ir.dotin.reactiveprogramming.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L03_SVRangeTest {

    @Test
    public void test1() {
        Flux<Integer> range = Flux.range(1, 50);

        StepVerifier.create(range)
                .expectNextCount(50)
                .verifyComplete();
    }

    @Test
    public void test2() {
        Flux<Integer> range = Flux.range(1, 50);

        StepVerifier.create(range)
                .thenConsumeWhile(p -> p < 100)
                .verifyComplete();
    }
}
