package ir.dotin.reactiveprogramming.S09_RepeatAndRetry;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class L02_RepeatWithCondition {

    public static AtomicInteger  atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        getNumbers()
                .repeat(() -> atomicInteger.get() < 14)
                .subscribe(Utils.subscriber());


    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 3)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> atomicInteger.getAndIncrement());
    }
}
