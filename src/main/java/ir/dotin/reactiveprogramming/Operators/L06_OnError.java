package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class L06_OnError {

    public static void main(String[] args) {

        Flux.range(0, 10)
                .log()
                .map(i -> 10 / (5 - i))
                // .onErrorReturn(-1)
                // .onErrorResume(e -> fallBack())
                .onErrorContinue((err, obj) -> {
                    System.out.println(err + " " + obj);
                })
                .subscribe(Utils.subscriber());

    }

    private static Mono<Integer> fallBack() {
        return Mono.fromSupplier(() -> Utils.faker().random().nextInt(100, 200));
    }

}
