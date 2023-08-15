package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L5_FluxFromRange {
    public static void main(String[] args) {


        Flux<Integer> range = Flux.range(3, 10);
        range
                .map(i -> Utils.faker().name().fullName())
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );

    }
}
