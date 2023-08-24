package ir.dotin.reactiveprogramming.S01_Mono;

import reactor.core.publisher.Mono;

public class L2_Monos {
    public static void main(String[] args) {

        // Publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono); // nothing happens
        mono.subscribe(
                i -> System.out.println(i)
        );


    }
}
