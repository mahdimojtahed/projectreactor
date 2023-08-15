package ir.dotin.reactiveprogramming.Mono;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Mono;

public class L3_MonoSubscribe {
    public static void main(String[] args) {


        Mono<String> mono = Mono.just("ball");

        // 1
        mono.subscribe(); // not doing anything but make publisher to emmit items

        // 2
        mono.subscribe(
                item -> System.out.println(item),
                err -> System.out.println(err.getMessage()),
                () -> System.out.println("Completed")
        );

        // onError
        Mono<Integer> integerMono = Mono.just("ball")
                .map(String::length)
                .map(l -> l / 0);

//        integerMono.subscribe(System.out::println); // not providing the behaviour on error

        integerMono.subscribe( // providing the behaviour
                Utils.onNext(),
                Utils.onError(),
                Utils.onComplete()
        );




    }
}
