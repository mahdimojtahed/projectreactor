package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;

import java.util.List;

public class L3_FluxFromArraylist {
    public static void main(String[] args) {

        List<String> strings = List.of("A", "B", "C");

        Flux.fromIterable(strings)
                .subscribe(Utils.onNext());

        Flux<Integer> integerFlux = Flux.fromArray(new Integer[]{2, 3, 1, 6, 12});
        integerFlux.subscribe(Utils.onNext());

    }
}
