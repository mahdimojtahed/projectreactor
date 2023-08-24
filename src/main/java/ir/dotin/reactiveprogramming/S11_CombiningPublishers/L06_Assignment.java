package ir.dotin.reactiveprogramming.S11_CombiningPublishers;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L06_Assignment {

    public static void main(String[] args) {

        final int carPrice = 10000;
        Flux.combineLatest(monthStream(), demandStream(), (m, d) -> (carPrice - (m * 100)) * d)
                .subscribe(Utils.subscriber()); 

    }

    private static Flux<Long> monthStream() {
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
    }

    private static Flux<Double> demandStream() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> Utils.faker().random().nextInt(8, 120) / 100d)
                .startWith(1d);
    }


}
