package ir.dotin.reactiveprogramming.S02_Flux;

import ir.dotin.reactiveprogramming.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class L10_FluxFromMono {

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("A");
        Flux<String> fromMono = Flux.from(mono);
        fromMono.subscribe(Utils.onNext());
    }

}
