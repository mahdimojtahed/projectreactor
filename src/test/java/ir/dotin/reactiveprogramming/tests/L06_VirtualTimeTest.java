package ir.dotin.reactiveprogramming.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class L06_VirtualTimeTest {


    @Test
    public void test1() {

        StepVerifier.withVirtualTime(() -> timeConsuming())
                .thenAwait(Duration.ofSeconds(30)) // it will assume 30 seconds have passed
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    @Test
    public void test2() {

        StepVerifier.withVirtualTime(() -> timeConsuming())
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(4))// assume we want to make sure nothing is happening for first 4 seconds
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }

    private Flux<String> timeConsuming() {
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map(i -> i + "a");
    }

}
