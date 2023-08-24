package ir.dotin.reactiveprogramming.S11_CombiningPublishers.helper;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ArmanBookStore {


    public static Flux<String> getBooks() {
        return Flux.range(1, Utils.faker().random().nextInt(1, 10))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "Arman " + Utils.faker().book().title())
                .filter(i -> Utils.faker().random().nextBoolean());
    }

}
