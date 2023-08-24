package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L05_CombineLatest {
    public static void main(String[] args) {

        Flux.combineLatest(getString(), getNumbers(), (s, n) -> s + 1)
                .subscribe(Utils.subscriber());


        Utils.sleepInSeconds(10);
    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumbers() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }


}
