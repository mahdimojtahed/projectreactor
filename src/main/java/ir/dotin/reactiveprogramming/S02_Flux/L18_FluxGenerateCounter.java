package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

public class L18_FluxGenerateCounter {

    public static void main(String[] args) {

        // assume we want to implement counter we created in l13.

        Flux.generate(
                        () -> 0, // initial state
                        (counter, sink) -> {
                            String country = Utils.faker().country().name();
                            sink.next(country);
                            if (counter >= 10 || country.equalsIgnoreCase("canada"))
                                sink.complete();
                            return counter + 1;
                        }
                )
                .subscribe(Utils.subscriber());
    }
}
