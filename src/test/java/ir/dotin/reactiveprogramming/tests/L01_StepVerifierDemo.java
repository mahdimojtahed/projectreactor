package ir.dotin.reactiveprogramming.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L01_StepVerifierDemo {

    @Test
    public void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);

        StepVerifier.create(just)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

}
