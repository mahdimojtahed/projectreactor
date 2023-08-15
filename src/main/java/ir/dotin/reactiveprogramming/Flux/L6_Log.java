package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L6_Log {
    public static void main(String[] args) {
        Flux.range(3, 10)
                .log()
                .map(i -> Utils.faker().name().fullName())
                .log()
                .subscribe(
                        Utils.onNext(),
                        Utils.onError(),
                        Utils.onComplete()
                );

    }
}
