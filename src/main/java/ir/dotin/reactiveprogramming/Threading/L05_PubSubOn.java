package ir.dotin.reactiveprogramming.Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L05_PubSubOn {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
                    printThreadName("create");
                    for (int i = 0; i < 4; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(i -> printThreadName("next1 " + i));


        flux
                .publishOn(Schedulers.parallel()) // affects below
                .doOnNext((i) -> printThreadName("next2 " + i))
                .subscribeOn(Schedulers.boundedElastic()) // affect the source
                .subscribe(v -> printThreadName("sub" + v));


        Utils.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName());
    }
}
