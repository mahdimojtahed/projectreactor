package ir.dotin.reactiveprogramming.Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L01_ThreadDemo {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(sink -> {
            printThreadName("create");
            sink.next(1);
        }).doOnNext(i -> printThreadName("next " + i));


        Runnable runnable = () -> flux.subscribe(v -> printThreadName("sub " + v));

        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Utils.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName() );
    }
}
