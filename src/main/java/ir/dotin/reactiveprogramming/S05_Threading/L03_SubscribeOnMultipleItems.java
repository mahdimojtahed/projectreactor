package ir.dotin.reactiveprogramming.S05_Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L03_SubscribeOnMultipleItems {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
            printThreadName("create");
            for (int i = 0; i < 5; i++) {
                sink.next(i);
                Utils.sleepInSeconds(1);
            }
            sink.complete();
        }).doOnNext(i -> printThreadName("next " + i));


         flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub 1" + v));

         flux
                .subscribeOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub 2" + v));

         flux
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub 3" + v));

        flux
                .subscribeOn(Schedulers.parallel())
                .subscribe(v -> printThreadName("sub 4" + v));


        Utils.sleepInSeconds(6);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName());
    }
}

