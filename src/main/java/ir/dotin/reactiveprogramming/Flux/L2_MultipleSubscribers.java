package ir.dotin.reactiveprogramming.Flux;

import reactor.core.publisher.Flux;

public class L2_MultipleSubscribers {
    public static void main(String[] args) {

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);

        Flux<Integer> evenFlux = integerFlux
                .filter(i -> i % 2 == 0);

        integerFlux
                .subscribe(o -> System.out.println("Sub1 : " + o));

        evenFlux
                .subscribe(o -> System.out.println("Sub2 : " + o));


    }
}
