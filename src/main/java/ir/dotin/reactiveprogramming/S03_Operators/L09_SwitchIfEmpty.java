package ir.dotin.reactiveprogramming.S03_Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L09_SwitchIfEmpty {

    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallBackMethod() )
                .subscribe(Utils.subscriber());

    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }

    private static Flux<Integer> fallBackMethod() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofMillis(200));
    }
}
