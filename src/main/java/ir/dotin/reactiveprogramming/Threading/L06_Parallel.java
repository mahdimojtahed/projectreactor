package ir.dotin.reactiveprogramming.Threading;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class L06_Parallel {

    public static void main(String[] args) {


        Flux.range(1, 10)
                .parallel(4)
                .runOn(Schedulers.boundedElastic())
                .doOnNext((i) -> printThreadName("next " + i))
                .sequential()
                .subscribe(v -> printThreadName("sub " + v));

        Utils.sleepInSeconds(5);
    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t Thread : " + Thread.currentThread().getName());
    }

}
