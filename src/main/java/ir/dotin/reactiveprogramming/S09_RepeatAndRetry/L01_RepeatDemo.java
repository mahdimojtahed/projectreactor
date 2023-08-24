package ir.dotin.reactiveprogramming.S09_RepeatAndRetry;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L01_RepeatDemo {

    public static void main(String[] args) {
        getNumbers()
                .repeat(2) // first time it run will be ignored and after that we resubscribe 2 more times
                // in the first time our subscribe will not received Complete signal ( we have only --complete which we added
                // in end of publisher this is not from subscriber )
                .subscribe(Utils.subscriber());


    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 3)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"));
    }
}
