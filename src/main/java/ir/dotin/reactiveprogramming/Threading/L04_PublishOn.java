package ir.dotin.reactiveprogramming.Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L04_PublishOn {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
                    printThreadName("create");
                    for (int i = 0; i < 5; i++) {
                        sink.next(i);
                        Utils.sleepInSeconds(1);
                    }
                    sink.complete();
                })
                .doOnNext(i -> printThreadName("next " + i));


        flux
                .publishOn(Schedulers.boundedElastic()) // assume this is a time-consuming task
                .doOnNext((i) -> printThreadName("next " + i))
                .subscribeOn(Schedulers.parallel()) // and this is a cpu intensive task
                .subscribe(v -> printThreadName("sub 1" + v));


        Utils.sleepInSeconds(6);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName());
    }
}
