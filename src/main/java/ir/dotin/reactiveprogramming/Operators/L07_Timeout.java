package ir.dotin.reactiveprogramming.Operators;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L07_Timeout {

    public static void main(String[] args) {


        getOrderNumbers()
                .timeout(Duration.ofSeconds(2), fallBackMethod())
//                .timeout(Duration.ofSeconds(5))
                .subscribe(Utils.subscriber());


        Utils.sleepInSeconds(60);


    }


    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
//                .delayElements(Duration.ofSeconds(5))
//                ;
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> fallBackMethod() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofMillis(200));
    }
}
