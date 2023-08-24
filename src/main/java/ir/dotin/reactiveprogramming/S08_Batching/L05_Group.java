package ir.dotin.reactiveprogramming.S08_Batching;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class L05_Group {

    public static void main(String[] args) {

        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i % 2) // this will return a key - which in this example is 0 and 1
                .subscribe(groupOfFlux -> process(groupOfFlux, groupOfFlux.key()));


        Utils.sleepInSeconds(60);
    }

    private static void process(Flux<Integer> flux, int key) {
        System.out.println("Called"); // this method will be invoked only 2 times , not for each flux
        flux.subscribe(i -> System.out.println("key: " + key + " item: " + i));
    }


}
