package ir.dotin.reactiveprogramming.tests;

import ir.dotin.reactiveprogramming.S08_Batching.helper.BookOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class L04_AssertTest {

    @Test
    public void test1() {
        Mono<BookOrder> bookOrderMono = Mono.fromSupplier(BookOrder::new);

        StepVerifier.create(bookOrderMono)
                .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
                .verifyComplete();
    }
}
