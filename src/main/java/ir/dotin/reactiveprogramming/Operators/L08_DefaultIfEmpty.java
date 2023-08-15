package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L08_DefaultIfEmpty {

    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Utils.subscriber());

    }

    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }
}
