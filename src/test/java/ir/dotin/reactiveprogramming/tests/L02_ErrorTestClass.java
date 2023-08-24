package ir.dotin.reactiveprogramming.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.BindException;

public class L02_ErrorTestClass {


    @Test
    public void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> oops = Flux.error(new RuntimeException("oops"));

        Flux<Integer> integerFlux = just.concatWith(oops);


        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .verifyError();
    }
    @Test
    public void test2() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Integer> oops = Flux.error(new RuntimeException("oops"));

        Flux<Integer> integerFlux = just.concatWith(oops);


        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .verifyError(BindException.class);
    }
}
