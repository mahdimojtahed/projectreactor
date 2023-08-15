package ir.dotin.reactiveprogramming.Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class L11_FluxToMono {
    public static void main(String[] args) {


        Mono<Integer> next = Flux.range(1, 10)
                .next();
        next.subscribe(Utils.onNext(), Utils.onError(), Utils.onComplete());

        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next().subscribe(Utils.onNext());

    }
}
