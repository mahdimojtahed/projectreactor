package ir.dotin.reactiveprogramming.S05_Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L02_SubscribeOn {


    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
            printThreadName("create");
            sink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));


        // it will start from bottom in the main thread
        // then it will face the subscribeOn so all the works will go
        // in the new thread
        Runnable runnable = () -> flux
                .doFirst(() -> System.out.println("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Utils.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName() );
    }

}
